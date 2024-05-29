package com.iktpreobuka.ednevnik.exeptions;

import java.util.stream.Collectors;

import org.springframework.security.access.AccessDeniedException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("Parametar '%s' mora biti tipa '%s'", ex.getName(), ex.getRequiredType().getSimpleName());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), message);
    }
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorMessage handleAccessDeniedException(AccessDeniedException ex) {
	    return new ErrorMessage(HttpStatus.FORBIDDEN.value(), ex.getMessage());
	}
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public ErrorMessage handleAllExceptions(Exception ex) {
//	    return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Došlo je do greške. Molimo pokušajte kasnije.");
//	}

	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleDataAccessException(DataAccessException ex) {
	    return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Greška prilikom pristupa podacima.");
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
	    return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Neispravan format zahteva.");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	    String errorMessage = ex.getBindingResult().getAllErrors().stream()
	                             .map(ObjectError::getDefaultMessage)
	                             .collect(Collectors.joining(", "));
	    return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errorMessage);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "Operacija nije uspela zbog kršenja ograničenja integriteta podataka. Molimo proverite unos.";
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), message);
    }
	
	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(HttpStatus.CONFLICT) // HttpStatus.CONFLICT (409) se često koristi za situacije gde nešto nije dozvoljeno zbog trenutnog stanja resursa
	public ErrorMessage handleIllegalStateException(IllegalStateException ex) {
	    return new ErrorMessage(HttpStatus.CONFLICT.value(), ex.getMessage());
	}
	
    // Klasa za formatiranje poruke o grešci
    public static class ErrorMessage {
        private int statusCode;
        private String message;
    
	    public ErrorMessage(int statusCode, String message) {
	        this.statusCode = statusCode;
	        this.message = message;
	    }

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
    }
}
