package br.com.zup.sistemamarketing.DTOs.produto.entrada;

import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.models.Produto;

import javax.validation.constraints.Size;
import java.util.List;

public class CadastraProdutoDTO {
    @Size(min = 3, max = 255)
    private String nome;

    private List<Categoria> categorias;

    public CadastraProdutoDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Categoria> getCategorias() { return categorias; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }

    public Produto converterDTOparaProduto(){
        Produto produto = new Produto();

        produto.setNome(this.nome);
        produto.setCategorias(categorias);

        return produto;
    }
}
