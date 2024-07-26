package projeto.training_location.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleBusinessException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleUnexpectedException(Exception e) {

        logger.error("Unexpected error: ", e);
        return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
    }
}
