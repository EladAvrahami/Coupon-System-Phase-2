package com.jb.Groupon.advice;

import com.jb.Groupon.Exceptions.AdminExceptions;
import com.jb.Groupon.Exceptions.TomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


//AOP - EXCEPTION in REST
@RestController
@ControllerAdvice
public class AdminControllerAdvice {
    @ExceptionHandler(value = {AdminExceptions.class, TomException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetails handleException (Exception e){
        return new ErrorDetails("Error",e.getMessage());
    }
}
