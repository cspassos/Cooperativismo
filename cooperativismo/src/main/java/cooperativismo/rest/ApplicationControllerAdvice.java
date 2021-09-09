package cooperativismo.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import cooperativismo.rest.exception.ApiErrors;

//Classe responsavel para tratar as mensagens de erro
@RestControllerAdvice
public class ApplicationControllerAdvice {

	//Vai pegar os @Valid e fazer o tratamento do retorno da mensagem
		@ExceptionHandler(MethodArgumentNotValidException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST) // erro 400
		public Object handleValidationErros( MethodArgumentNotValidException ex ) {
			BindingResult bindingResult = ex.getBindingResult();//resultado da validação
			List<String> messages = bindingResult.getAllErrors()
					.stream()
					.map(objectError -> objectError.getDefaultMessage())
					.collect(Collectors.toList());
			
			return new ApiErrors(messages);
		}
		
		//tratar o NOT_FOUND
		@ExceptionHandler(ResponseStatusException.class)
	    public ResponseEntity<ApiErrors> handleResponseStatusException(ResponseStatusException ex){
	        String mensagemErro = ex.getReason();
	        HttpStatus codigoStatus = ex.getStatus();
	        ApiErrors apiErrors = new ApiErrors(mensagemErro);
	        return new ResponseEntity<ApiErrors>(apiErrors, codigoStatus);
	    }
}
