package com.franchise.infrastructure.adapter.in.rest.exception;

import static com.franchise.infrastructure.utils.constants.MessageConstants.INVALID_REQUEST;
import static com.franchise.infrastructure.utils.constants.MessageConstants.UNEXPECTED_ERROR;

import com.franchise.domain.exception.BranchNotFoundException;
import com.franchise.domain.exception.FranchiseNotFoundException;
import com.franchise.domain.exception.ProductNotFoundException;
import java.time.Instant;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({FranchiseNotFoundException.class, BranchNotFoundException.class, ProductNotFoundException.class})
    ResponseEntity<Map<String, Object>> notFound(RuntimeException exception) {
        return error(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Map<String, Object>> invalidArgument(IllegalArgumentException exception) {
        return error(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<Map<String, Object>> malformedBody(HttpMessageNotReadableException exception) {
        return error(HttpStatus.BAD_REQUEST, INVALID_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(fieldError -> fieldError.getDefaultMessage())
                .orElse(INVALID_REQUEST);
        return error(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Map<String, Object>> generic(Exception exception) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, UNEXPECTED_ERROR);
    }

    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String message) {
        Map<String, Object> body = Map.of(
                "success", false,
                "message", message,
                "timestamp", Instant.now().toString()
        );
        return ResponseEntity.status(status).body(body);
    }
}
