package br.com.zup.sistemamarketing.DTOs.contato.saida;

import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ContatoDTO {
    @NotBlank
    @Size(min = 3, max = 255)
    private String nomeCompleto;

    @Email(message = "{validacao.email_invalido}")
    private String email;

    @NotBlank
    @Size(min =10, message = "{validacao.telefone}")
    private String telefone;

    private List<Produto> produtos;

    public ContatoDTO() {}

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }

    public static ContatoDTO converterContatoParaDTO(Contato contato) {
        ContatoDTO contatoDTO = new ContatoDTO();

        contatoDTO.setNomeCompleto(contato.getNomeCompleto());
        contatoDTO.setEmail(contato.getEmail());
        contatoDTO.setTelefone(contato.getTelefone());
        contatoDTO.setProdutos(contato.getProdutos());

        return contatoDTO;
    }

    public static Iterable<ContatoDTO> converterIterableDeContatosParaDTO(Iterable<Contato> contatos) {
        List<ContatoDTO> contatoDTOS = new ArrayList<>();

        for (Contato contato : contatos ) {
            contatoDTOS.add(ContatoDTO.converterContatoParaDTO(contato));
        }

        return contatoDTOS;
    }
}
