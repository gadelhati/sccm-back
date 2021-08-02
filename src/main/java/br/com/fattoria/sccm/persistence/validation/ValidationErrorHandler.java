package br.com.fattoria.sccm.persistence.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationMessage> handlerMethodArgumentNotValid(final MethodArgumentNotValidException argumentNotValidException){
		
		List<ValidationMessage> validationMessages = new ArrayList<ValidationMessage>();
		
		List<FieldError> fieldErrors = argumentNotValidException.getBindingResult().getFieldErrors();
		
		for (FieldError fieldError : fieldErrors) {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			ValidationMessage validationMessage = new ValidationMessage(fieldError.getField(), message);
			validationMessages.add(validationMessage);
		}
		
		return validationMessages;
		
	}
	
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody List<ValidationMessage> handleConstraintViolation(final ConstraintViolationException exception) {
    	
       List<ValidationMessage> validationMessages = new ArrayList<ValidationMessage>();
       
       Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
       
        for (ConstraintViolation<?> violation : constraintViolations) {
        	
        	ValidationMessage validationMessage = new ValidationMessage(violation.getPropertyPath().toString(), violation.getMessage(), violation.getInvalidValue());
        	
            validationMessages.add(validationMessage);
        }
        return validationMessages;
    }
    
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public @ResponseBody List<ValidationMessage> handleConstraintViolation(final org.hibernate.exception.ConstraintViolationException exception) {
    	
    	List<ValidationMessage> validationMessages = new ArrayList<ValidationMessage>();
    	
    	String [] constraint = exception.getConstraintName().split("_");
    	
    	if(constraint != null && constraint.length == 3) {
        	String tabela = constraint[0];
	    	String campo = constraint[1];
	    	String tipo = constraint[2];
	    	
	    	String mensagem = tipo.contains("unique") ? "Já existe "+tabela+" com "+campo+" informada" : exception.getConstraintName();
	    	
	    	validationMessages.add(new ValidationMessage(campo , mensagem));
    	}else {
    		validationMessages.add(new ValidationMessage(exception.getConstraintName(), exception.getMessage()));
    	}
    	
        return validationMessages;
    }
   
}
