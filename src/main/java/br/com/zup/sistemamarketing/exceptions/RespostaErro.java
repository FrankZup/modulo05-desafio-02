package br.com.zup.sistemamarketing.exceptions;

import java.util.List;

public class RespostaErro {
    private String tipoErro;
    private int codigo;
    private String status;
    private List<ObjetoErro> objetoErro;

    public RespostaErro(String tipoErro, int codigo, String status, List<ObjetoErro> objetoErro) {
        this.tipoErro = tipoErro;
        this.codigo = codigo;
        this.status = status;
        this.objetoErro = objetoErro;
    }

    public RespostaErro() { }

    public String getTipoErro() { return tipoErro; }
    public void setTipoErro(String tipoErro) { this.tipoErro = tipoErro; }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<ObjetoErro> getObjetoErro() { return objetoErro; }
    public void setObjetoErro(List<ObjetoErro> objetoErro) { this.objetoErro = objetoErro; }

}
