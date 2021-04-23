package br.com.zup.sistemamarketing.exceptions;

public class ExcluirCategoriaException extends ExcecaoBasica {
    public ExcluirCategoriaException(){
        super("Imposível deletar uma categoria vinculada ao produto", 422,
                "id", "Unprocessable Entity");
    }
}
