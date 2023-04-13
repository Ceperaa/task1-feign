package ru.clevertec.task1feign.controller;

import feign.FeignException;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.task1feign.exception.FieldNullException;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {

    @ExceptionHandler(value = FieldNullException.class)
    public ResponseEntity<ExceptionObject> response510(Exception e) {
        return new ResponseEntity<>(aggregate(e, HttpStatus.NOT_EXTENDED),
                HttpStatus.NOT_EXTENDED);
    }

    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<ExceptionObject> response500(Exception e) {
        return new ResponseEntity<>(aggregate(e, HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
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


