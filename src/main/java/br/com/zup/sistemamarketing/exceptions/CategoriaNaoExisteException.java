package br.com.zup.sistemamarketing.exceptions;

public class CategoriaNaoExisteException extends ExcecaoBasica {
    public CategoriaNaoExisteException(){
        super("Categoria não existe!", 404, "id", "Not Found");
    }
}
