package com.example.ProductComparison.api.Exceptions;

import com.example.ProductComparison.api.model.EmptyDataResponse;
import com.example.ProductComparison.api.model.Notifications;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class, Exception.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        Notifications notification = new Notifications();
        notification.setCode("ECOMPAREERROR");
        notification.setMessage(ex.getMessage());
        EmptyDataResponse emptyDataResponse = new EmptyDataResponse();
        List<Notifications> notifications = new ArrayList<>();
        notifications.add(notification);
        emptyDataResponse.setNotifications(notifications);

        return new ResponseEntity<>(emptyDataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
