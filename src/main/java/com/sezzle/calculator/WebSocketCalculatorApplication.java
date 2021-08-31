package com.sezzle.calculator;

import javax.script.ScriptEngineManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebSocketCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketCalculatorApplication.class, args);
	}

}
