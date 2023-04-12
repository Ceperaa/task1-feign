package ru.clevertec.task1feign.controller;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {

    @ExceptionHandler(value = {
            Exception.class
    })
    public ResponseEntity<ExceptionObject> response404(Exception e) {
        return new ResponseEntity<>(aggregate(e, HttpStatus.NOT_FOUND)
                , HttpStatus.NOT_FOUND);

    }

    private ExceptionObject aggregate(Exception e, HttpStatus status) {
        return ExceptionObject.builder()
                .code(status.value())
                .status(String.valueOf(status))
                .message(e.getMessage()).build();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class ExceptionObject {

        private int code;
        private String status;
        private String message;
    }
}


