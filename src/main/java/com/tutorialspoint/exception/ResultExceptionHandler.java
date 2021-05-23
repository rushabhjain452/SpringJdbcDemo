package com.tutorialspoint.exception;

import com.tutorialspoint.response.Result;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    private ResponseEntity<Result<String>> handleException(Exception ex) {
        Result<String> result = new Result(500, "unfortunately! there was some error at the server side.");
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResultException.class})
    private ResponseEntity<Result<Object>> handleResultException(ResultException ex) {
        Result<Object> result = ex.getResult();
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<String> result = new Result<>(400, "Error! unable to parse the given data");
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<String> result = new Result<>(400, "Missing! Object body missing");
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<String> result = new Result<>(400, "Error! unable to read the given data");
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
