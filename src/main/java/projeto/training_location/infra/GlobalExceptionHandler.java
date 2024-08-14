package projeto.training_location.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import projeto.training_location.service.exception.BusinessException;
import projeto.training_location.service.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity NotFoundException(Exception e) {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleUnexpectedException(Exception e) {

        logger.error("Unexpected error: ", e);
        return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
    }
}
