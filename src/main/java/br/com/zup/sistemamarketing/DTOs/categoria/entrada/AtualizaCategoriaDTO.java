package br.com.zup.sistemamarketing.DTOs.categoria.entrada;

import br.com.zup.sistemamarketing.models.Categoria;

import javax.validation.constraints.Size;

public class AtualizaCategoriaDTO {

    @Size(min = 3, max = 255)
    private String nome;

    public AtualizaCategoriaDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Categoria atualizarDTOcategoria(int id){
        Categoria categoria = new Categoria();

        categoria.setId(id);
        categoria.setNome(this.nome);

        return categoria;
    }

}
