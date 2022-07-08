package br.com.estevam.forum.config.validation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@RestControllerAdvice
public class FormValidationHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorFormDataDto> formDataHandle(MethodArgumentNotValidException exception) {
		
		return exception.getFieldErrors().stream().map(this::toErrorFormData).collect(Collectors.toList());
		
	}
	
	private ErrorFormDataDto toErrorFormData(FieldError fieldError) {
		
		var message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
		return new ErrorFormDataDto(fieldError.getField(), message);
	}
	
}
