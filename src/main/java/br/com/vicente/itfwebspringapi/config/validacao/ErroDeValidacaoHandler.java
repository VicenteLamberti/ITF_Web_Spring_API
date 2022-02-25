package br.com.vicente.itfwebspringapi.config.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.vicente.itfwebspringapi.dto.ErroDeFormularioDto;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource  messageSource;

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(x -> {
			String mensagem = messageSource.getMessage(x, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(x.getField(), mensagem);
			dto.add(erro);
			
		});
		
		
		return dto;
	}
	
	@ExceptionHandler
	public ResponseEntity<?> handle(ObjectNotFoundException exception) {
		return new ResponseEntity<>("Ocorreu um erro ao tentar realizar operações com esse id!",HttpStatus.NOT_FOUND);
	}
	
//	public ResponseEntity<?> handle(NoSuchElementException exception){
//		return new ResponswEntity<>("Ocorreu um erro ao real")
//	}
}
