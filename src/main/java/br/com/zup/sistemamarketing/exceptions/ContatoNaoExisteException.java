package br.com.zup.sistemamarketing.exceptions;

public class ContatoNaoExisteException extends ExcecaoBasica {
    public ContatoNaoExisteException(){
        super("Contato não existe!", 404, "e-mail","Not Found");
    }
}
