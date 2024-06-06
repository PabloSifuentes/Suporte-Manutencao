package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@Entity
public class Producao {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducao;

    private LocalDateTime dataHora;
@ManyToOne
@JoinColumn(name = "idPecas", referencedColumnName = "idPecas")
    private Pecas pecas;

@OneToMany(mappedBy = "producao")
private List<Qualidade> listaDeInspecao;

    private Long quantidadeProduzada;

    private String estado;
    public Producao(){
    }

    public Producao(Long idProducao, LocalDateTime dataHora, Pecas pecas, List<Qualidade> listaDeInspecao, Long quantidadeProduzada, String estado) {
        this.idProducao = idProducao;
        this.dataHora = dataHora;
        this.pecas = pecas;
        this.listaDeInspecao = listaDeInspecao;
        this.quantidadeProduzada = quantidadeProduzada;
        this.estado = estado;
    }

    public Long getIdProducao() {
        return idProducao;
    }

    public void setIdProducao(Long idProducao) {
        this.idProducao = idProducao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Pecas getPecas() {
        return pecas;
    }

    public void setPecas(Pecas pecas) {
        this.pecas = pecas;
    }

    public List<Qualidade> getListaDeInspecao() {
        return listaDeInspecao;
    }

    public void setListaDeInspecao(List<Qualidade> listaDeInspecao) {
        this.listaDeInspecao = listaDeInspecao;
    }

    public Long getQuantidadeProduzada() {
        return quantidadeProduzada;
    }

    public void setQuantidadeProduzada(Long quantidadeProduzada) {
        this.quantidadeProduzada = quantidadeProduzada;
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

        Producao producao = (Producao) o;

        if (!idProducao.equals(producao.idProducao)) return false;
        if (!Objects.equals(dataHora, producao.dataHora)) return false;
        if (!Objects.equals(pecas, producao.pecas)) return false;
        if (!Objects.equals(listaDeInspecao, producao.listaDeInspecao))
            return false;
        if (!Objects.equals(quantidadeProduzada, producao.quantidadeProduzada))
            return false;
        return Objects.equals(estado, producao.estado);
    }

    @Override
    public int hashCode() {
        int result = idProducao.hashCode();
        result = 31 * result + (dataHora != null ? dataHora.hashCode() : 0);
        result = 31 * result + (pecas != null ? pecas.hashCode() : 0);
        result = 31 * result + (listaDeInspecao != null ? listaDeInspecao.hashCode() : 0);
        result = 31 * result + (quantidadeProduzada != null ? quantidadeProduzada.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Producao{" +
                "idProducao=" + idProducao +
                ", dataHora=" + dataHora +
                ", pecas=" + pecas +
                ", listaDeInspecao=" + listaDeInspecao +
                ", quantidadeProduzada=" + quantidadeProduzada +
                ", estado='" + estado + '\'' +
                '}';
    }
}
