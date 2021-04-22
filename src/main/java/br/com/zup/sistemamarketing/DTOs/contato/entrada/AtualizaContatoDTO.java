package br.com.zup.sistemamarketing.DTOs.contato.entrada;

import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

public class AtualizaContatoDTO {
    @Size(min = 3, max = 255)
    private String nomeCompleto;

    @Email(message = "{validacao.email_invalido}")
    private String email;

    @Size(min =10, message = "{validacao.telefone}")
    private String telefone;

    private List<Produto> produtos;

    public AtualizaContatoDTO() {}

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }

    public Contato atualizarDTOcontato(String email){
        Contato contato = new Contato();

        contato.setNomeCompleto(this.nomeCompleto);
        contato.setEmail(email);
        contato.setTelefone(this.telefone);
        contato.setProdutos(this.produtos);

        return contato;
    }
}
