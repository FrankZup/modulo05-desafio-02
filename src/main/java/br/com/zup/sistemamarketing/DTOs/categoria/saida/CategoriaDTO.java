package br.com.zup.sistemamarketing.DTOs.categoria.saida;

import br.com.zup.sistemamarketing.models.Categoria;

import javax.validation.constraints.Size;

public class CategoriaDTO {
    private Integer id;

    @Size(min = 3, max = 255)
    private String nome;

    public CategoriaDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public static CategoriaDTO converterCategoriaParaDTO(Categoria categoria){
        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNome(categoria.getNome());

        return categoriaDTO;
    }
}
