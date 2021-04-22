package br.com.zup.sistemamarketing.DTOs.categoria.entrada;

import br.com.zup.sistemamarketing.models.Categoria;

import javax.validation.constraints.Size;

public class CadastraCategoriaDTO {
    @Size(min = 3, max = 255)
    private String nome;

    public CadastraCategoriaDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Categoria converterDTOparaCategoria(){
        Categoria categoria = new Categoria();

        categoria.setNome(this.nome);

        return categoria;
    }
}

