package br.com.zup.sistemamarketing.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "contatos")
public class Contato {
    @Id
    private String email;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String telefone;

    @ManyToMany
    private List<Produto> produtos;

    public Contato() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }


}
