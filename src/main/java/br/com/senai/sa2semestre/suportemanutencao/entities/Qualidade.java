package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Qualidade {
@Id
@GeneratedValue
    private Long idInspecao;
@ManyToOne
@JoinColumn(name = "idProducao", referencedColumnName = "idProducao")
    private Producao producao;

private LocalDateTime dataHora;

private String resultado;

private String comentarios;

    public Qualidade(){
    }
    public Qualidade(Long idInspecao, Producao producao, LocalDateTime dataHora, String resultado, String comentarios) {
        this.idInspecao = idInspecao;
        this.producao = producao;
        this.dataHora = dataHora;
        this.resultado = resultado;
        this.comentarios = comentarios;
    }

    public Long getIdInspecao() {
        return idInspecao;
    }

    public void setIdInspecao(Long idInspecao) {
        this.idInspecao = idInspecao;
    }

    public Producao getProducao() {
        return producao;
    }

    public void setProducao(Producao producao) {
        this.producao = producao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Qualidade qualidade = (Qualidade) o;

        if (!idInspecao.equals(qualidade.idInspecao)) return false;
        if (!Objects.equals(producao, qualidade.producao)) return false;
        if (!Objects.equals(dataHora, qualidade.dataHora)) return false;
        if (!Objects.equals(resultado, qualidade.resultado)) return false;
        return Objects.equals(comentarios, qualidade.comentarios);
    }

    @Override
    public int hashCode() {
        int result = idInspecao.hashCode();
        result = 31 * result + (producao != null ? producao.hashCode() : 0);
        result = 31 * result + (dataHora != null ? dataHora.hashCode() : 0);
        result = 31 * result + (resultado != null ? resultado.hashCode() : 0);
        result = 31 * result + (comentarios != null ? comentarios.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Qualidade{" +
                "idInspecao=" + idInspecao +
                ", producao=" + producao +
                ", dataHora=" + dataHora +
                ", resultado='" + resultado + '\'' +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}
