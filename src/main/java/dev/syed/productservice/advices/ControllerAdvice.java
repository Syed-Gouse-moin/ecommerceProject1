package dev.syed.productservice.advices;

import com.stripe.exception.StripeException;
import dev.syed.productservice.dtos.errorDto;
import dev.syed.productservice.exceptions.ProductNotFoundException;
import dev.syed.productservice.exceptions.CategoryNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e) {
        errorDto errorMessage = new errorDto();
        errorMessage.setMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> CategoryNotFoundException(CategoryNotFoundException e) {
        errorDto errorMessage = new errorDto();
        errorMessage.setMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(StripeException.class)
    public ResponseEntity<Object> handleStripeException(StripeException e) {
        errorDto errorMessage = new errorDto();
        errorMessage.setMessage("Stripe exception");
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
}

