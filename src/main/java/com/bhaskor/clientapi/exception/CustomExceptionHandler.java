package com.bhaskor.clientapi.exception;

import com.bhaskor.clientapi.payload.response.JsonResponse;
import com.bhaskor.clientapi.util.StringUtil;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<JsonResponse> badRequestException(final BadRequestException e) {
        return new ResponseEntity<JsonResponse>(new JsonResponse(false, null, "Error Ocurred : " + e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class) // This will catch ConstraintViolationException.class
    public ResponseEntity<JsonResponse> dataIntegrityViolationException(final DataIntegrityViolationException e) {
        try {
            String message = e.getRootCause().getMessage();
            int firstIndex = message.indexOf("(");
            int lastIndex = message.indexOf(")", firstIndex);
            message = message.substring(firstIndex + 1, lastIndex);
            return new ResponseEntity<JsonResponse>(
                    new JsonResponse(false, null,
                            "Unique Constrain Violation : " + StringUtil.snakeToCamel(message.toLowerCase())),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<JsonResponse>(new JsonResponse(false, null, "Unique Constrain Violation !"),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
