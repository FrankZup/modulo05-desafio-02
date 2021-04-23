package br.com.zup.sistemamarketing.exceptions;

public class ProdutoNaoExisteException extends ExcecaoBasica {
    public ProdutoNaoExisteException(){
        super("Produto não existe!", 404, "id", "Not Found");
    }
}
