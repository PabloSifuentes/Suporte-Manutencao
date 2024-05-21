package Model;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Equipamento {

     private Long idEquipamento;

     private String  tipoDeEquipamento;

     private String descricao;

     private String estado;

    public Equipamento(Long idEquipamento, String tipoDeEquipamento, String descricao, String estado) {
        this.idEquipamento = idEquipamento;
        this.tipoDeEquipamento = tipoDeEquipamento;
        this.descricao = descricao;
        this.estado = estado;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipamento that = (Equipamento) o;

        if (!idEquipamento.equals(that.idEquipamento)) return false;
        if (!Objects.equals(tipoDeEquipamento, that.tipoDeEquipamento))
            return false;
        if (!Objects.equals(descricao, that.descricao)) return false;
        return Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        int result = idEquipamento.hashCode();
        result = 31 * result + (tipoDeEquipamento != null ? tipoDeEquipamento.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "idEquipamento=" + idEquipamento +
                ", tipoDeEquipamento='" + tipoDeEquipamento + '\'' +
                ", descricao='" + descricao + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
