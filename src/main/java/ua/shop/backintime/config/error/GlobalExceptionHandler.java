package ua.shop.backintime.config.error;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.shop.backintime.user.service.exception.NoteNotFoundException;
import ua.shop.backintime.user.service.exception.UserAlreadyExistException;
import ua.shop.backintime.user.service.exception.UserIncorrectPasswordException;
import ua.shop.backintime.user.service.exception.UserNotFoundException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, Map<String, List<String>>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, List<String>> result = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> {
                    if (result.containsKey(e.getField())) {
                        result.get(e.getField()).add(e.getDefaultMessage());
                    } else {
                        result.put(e.getField(), Collections.singletonList(e.getDefaultMessage()));
                    }
                });
        return new ResponseEntity<>(getErrorsMap(result), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            NoteNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<Map<String, List<String>>> notFoundException(Exception ex) {
        return getErrorsMap(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {FileUploadException.class})
    public ResponseEntity<Map<String, List<String>>> fileUploadException(FileUploadException ex) {
        return getErrorsMap(ex, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(value = {
            UserAlreadyExistException.class,
            UserIncorrectPasswordException.class
    })
    public ResponseEntity<Map<String, List<String>>> conflictException(Exception ex) {
        return getErrorsMap(ex, HttpStatus.CONFLICT);
    }

    /* Helpers */

    private Map<String, Map<String, List<String>>> getErrorsMap(Map<String, List<String>> errors) {
        Map<String, Map<String, List<String>>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    private ResponseEntity<Map<String, List<String>>> getErrorsMap(Throwable ex, HttpStatus status) {
        Map<String, List<String>> map = new HashMap<>();
        map.put("errors", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(map, new HttpHeaders(), status);
    }

}
