package com.ticket.spring.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@ControllerAdvice
public class LikeErrorResponse {

    private static Logger logger= Logger.getLogger(LikeErrorResponse.class.getName());

    @ExceptionHandler
    public ResponseEntity<Error> runtimeExc(RuntimeException e){
        Error error=new Error(HttpStatus.BAD_GATEWAY.value(), e.getMessage(), LocalDateTime.now());
        logger.info(error+"");
        return new ResponseEntity<>(error,HttpStatus.BAD_GATEWAY);
    }
}
