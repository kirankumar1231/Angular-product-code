package nl.retail.store.grocerystore.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GenericExceptionHandler {
    @ExceptionHandler(value = { ProductNotFoundException.class })
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(createError(ex.getCode(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { InputDataInvalidException.class })
    public ResponseEntity<Object> handleInputDataInvalidException(InputDataInvalidException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(createError(ex.getCode(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Error createError(final String code, final String message) {
        return Error.builder()
                .code(code)
                .message(message)
                .build();
    }
}
