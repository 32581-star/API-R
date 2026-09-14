package com.des.backend.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(

    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> fieldErrors) {

        public ApiError(HttpStatus status, String message, String path) {
            this(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, path, null);
        }

        public ApiError(HttpStatus status, String message, String path, Map<String, String> fieldErrors) {
            this(LocalDateTime.now(), status.value(),
            status.getReasonPhrase(), message, path, fieldErrors);
        }
} 
