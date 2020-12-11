package com.visoft.helper.service.controller.handler;

import com.visoft.helper.service.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    @ExceptionHandler(value = Throwable.class)
    private ResponseEntity<ErrorMessage> throwable(Throwable throwable) {
        return buildMessage(throwable, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    private ResponseEntity<ErrorMessage> accessDeniedException(AccessDeniedException e) {
        return buildMessage(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = CustomException.class)
    private ResponseEntity<ErrorMessage> unauthorizedException(CustomException exception) {
        return buildMessage(exception);
    }

    private ResponseEntity<ErrorMessage> buildMessage(CustomException customException) {
        return new ResponseEntity<>(
                new ErrorMessage(
                        customException.getHttpStatus(),
                        LocalDateTime.now(),
                        customException.getLocalizedMessage(),
                        customException.getClass().getSimpleName()
                ),
                customException.getHttpStatus());
    }

    private ResponseEntity<ErrorMessage> buildMessage(Throwable throwable, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                new ErrorMessage(
                        httpStatus,
                        LocalDateTime.now(),
                        throwable.getLocalizedMessage(),
                        throwable.getClass().getSimpleName()
                ),
                httpStatus);
    }
}