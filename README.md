spring-websocket-calc
=====================

Collab Calculator application using AngularJS and Spring WebSockets (STOMP over WebSockets)

![Spring WebSocket Calculator](https://github.com/rajeevmassey/spring-websocket-calc-master "Spring WebSocket Calc Git")
## Features
- Built with Spring Boot
- User login
- MMathematical Expression broadcasting and private messages (filtering profanities)
- Presence tracking sending notifications when users join / leave
- Broadcast notifications when users are typing
- WebSockets stats exposed at /stats
- WebSocket security with Spring Security
- Spring Session integration

## Credentials for the Admin
- Username : Rajeev (Your Name)
- Password : Admin

## Running the app

If you directly wanna run the jar run this command (If the redis server is configured): 
$ java -jar spring-websocket-calc-master-2.0.0.M1.jar

or 

You can also clone the project from github and run the following command in your terminal :
$ gradle bootRun

## URL after running
http://localhost:8080/