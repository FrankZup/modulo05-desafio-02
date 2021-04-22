package br.com.zup.sistemamarketing.DTOs.produto.saida;

import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.models.Produto;

import javax.validation.constraints.Size;
import java.util.List;

public class ProdutoDTO {

    private Integer id;

    @Size(min = 3, max = 255)
    private String nome;

    private List<Categoria> categorias;

    public ProdutoDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Categoria> getCategorias() { return categorias; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }

    public static ProdutoDTO converterProdutoParaDTO(Produto produto){
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setCategorias(produto.getCategorias());

        return produtoDTO;
    }
}
