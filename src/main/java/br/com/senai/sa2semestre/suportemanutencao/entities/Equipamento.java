package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long idEquipamento;

     private String  tipoDeEquipamento;

     private String descricao;

     private String estado;

     @OneToMany(mappedBy = "equipamento")
     private List<Manutencao> listaDeManutencoes;

    public Equipamento(Long idEquipamento, String tipoDeEquipamento, String descricao, String estado, List<Manutencao> listaDeManutencoes) {
        this.idEquipamento = idEquipamento;
        this.tipoDeEquipamento = tipoDeEquipamento;
        this.descricao = descricao;
        this.estado = estado;
        this.listaDeManutencoes = listaDeManutencoes;
    }

    public Equipamento(){

    }

    public Long getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(Long idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getTipoDeEquipamento() {
        return tipoDeEquipamento;
    }

    public void setTipoDeEquipamento(String tipoDeEquipamento) {
        this.tipoDeEquipamento = tipoDeEquipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Manutencao> getListaDeManutencoes() {
        return listaDeManutencoes;
    }

    public void setListaDeManutencoes(List<Manutencao> listaDeManutencoes) {
        this.listaDeManutencoes = listaDeManutencoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipamento that = (Equipamento) o;

        if (!idEquipamento.equals(that.idEquipamento)) return false;
        if (!Objects.equals(tipoDeEquipamento, that.tipoDeEquipamento))
            return false;
        if (!Objects.equals(descricao, that.descricao)) return false;
        if (!Objects.equals(estado, that.estado)) return false;
        return Objects.equals(listaDeManutencoes, that.listaDeManutencoes);
    }

    @Override
    public int hashCode() {
        int result = idEquipamento.hashCode();
        result = 31 * result + (tipoDeEquipamento != null ? tipoDeEquipamento.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (listaDeManutencoes != null ? listaDeManutencoes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "idEquipamento=" + idEquipamento +
                ", tipoDeEquipamento='" + tipoDeEquipamento + '\'' +
                ", descricao='" + descricao + '\'' +
                ", estado='" + estado + '\'' +
                ", listaDeManutencoes=" + listaDeManutencoes +
                '}';
    }
}
