package br.com.zup.sistemamarketing.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ManipuladorExcecao extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ObjetoErro> objetosDeErro = criarObjetoDeErro(ex);
        RespostaErro respostaDeErro = new RespostaErro("Validação", status.value(),
                status.getReasonPhrase(), objetosDeErro);

        return ResponseEntity.status(status).body(respostaDeErro);
    }

    private List<ObjetoErro> criarObjetoDeErro(MethodArgumentNotValidException exception) {
        List<ObjetoErro> objetosDeErro = new ArrayList<>();
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        for (FieldError listaDeErros : errors) {
            ObjetoErro objetoDeErro = new ObjetoErro(listaDeErros.getDefaultMessage(),
                    listaDeErros.getField());
            objetosDeErro.add(objetoDeErro);
        }

        return objetosDeErro;
    }

    @ExceptionHandler({ExcecaoBasica.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespostaErro manipularRuntimeException(ExcecaoBasica erro){
        ObjetoErro objetoDeErro = new ObjetoErro(erro.getMessage(), erro.getCampo());
        RespostaErro respostaDeErro = new RespostaErro(erro.getMessage(), erro.getStatus(),
                erro.getMotivo(),  Arrays.asList(objetoDeErro));

        return respostaDeErro;
    }
}
