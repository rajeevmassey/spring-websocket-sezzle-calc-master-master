package com.sezzle.calculator.web;

import com.sezzle.calculator.domain.Calculation;
import com.sezzle.calculator.domain.SessionProfanity;
import com.sezzle.calculator.event.LoginEvent;
import com.sezzle.calculator.event.ParticipantRepository;
import com.sezzle.calculator.exception.TooMuchProfanityException;
import com.sezzle.calculator.util.ExpressionEvaluator;
import com.sezzle.calculator.util.ProfanityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import javax.script.ScriptException;
import java.security.Principal;
import java.util.Collection;

/**
 * Controller that handles WebSocket chat messages
 *
 * @author Rajeev Massey
 */
@Controller
public class ChatController {

    @Autowired
    private ProfanityChecker profanityFilter;

    @Autowired
    private SessionProfanity profanity;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @SubscribeMapping("/chat.participants")
    public Collection<LoginEvent> retrieveParticipants() {
        return participantRepository.getActiveSessions().values();
    }

    @MessageMapping("/chat.message")
    public Calculation filterMessage(@Payload Calculation calculation, Principal principal) throws ScriptException {
        checkProfanityAndSanitize(calculation);
        evaluateString(calculation);
        calculation.setUsername(principal.getName());
        return calculation;
    }

    @MessageMapping("/chat.private.{username}")
    public void filterPrivateMessage(@Payload Calculation calculation, @DestinationVariable("username") String username,
                                     Principal principal) throws ScriptException {
        checkProfanityAndSanitize(calculation);
        evaluateString(calculation);
        calculation.setUsername(principal.getName());

        simpMessagingTemplate.convertAndSend("/user/" + username + "/exchange/amq.direct/chat.message", calculation);
    }

    private void checkProfanityAndSanitize(Calculation message) {
        long profanityLevel = profanityFilter.getMessageProfanity(message.getExpression());
        profanity.increment(profanityLevel);
        message.setExpression(profanityFilter.filter(message.getExpression()));
    }

    private void evaluateString(Calculation calculation) {
        String result = "";
        result = String.valueOf(ExpressionEvaluator.evaluate(calculation.getExpression()).join());
        calculation.setExpression(calculation.getExpression() + " = " + result);
    }

    @MessageExceptionHandler
    @SendToUser(value = "/exchange/amq.direct/errors", broadcast = false)
    public String handleEvalString(Exception e) {
        return "Please Enter a correct Mathematical Expressions e.g 2+3+3";
    }
}