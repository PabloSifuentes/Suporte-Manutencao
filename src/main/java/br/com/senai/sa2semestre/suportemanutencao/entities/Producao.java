package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Entity Indica que a classe é uma entidade JPA e um objeto que será mapeado para uma tabela no banco de dados.
 * @Table Especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
 */
@Entity
@Table(name = "producao")
public class Producao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducao;

    @Column(name = "dataHora")
    private LocalDateTime dataHora;
    @ManyToOne
    @JoinColumn(name = "idPecas", referencedColumnName = "idPecas")
    private Pecas pecas;

    @OneToMany(mappedBy = "producao")
    private Set<Qualidade> listaDeInspecao = new HashSet<>();

    @Column(name = "quantidadeProduzida")
    private Long quantidadeProduzida;

    @Column(name = "estado")
    private String estado;

    /**
     * Construtor Padrão é necessário para a JPA instanciar a entidade.
     */
    public Producao(){
    }

    /**
     * Construtor com Parâmetros facilita a criação de instâncias da entidade
     * com valores especificos.
     * @param idProducao O identificador único da produção.
     * @param dataHora A data e hora de produção.
     * @param pecas A peça associada há produção.
     * @param listaDeInspecao Conjunto de inspeções associados a produção.
     * @param quantidadeProduzida A quantidade produzida pela produção.
     * @param estado O estado atual da produção.
     */
    public Producao(Long idProducao, LocalDateTime dataHora, Pecas pecas, Set<Qualidade> listaDeInspecao, Long quantidadeProduzida, String estado) {
        this.idProducao = idProducao;
        this.dataHora = dataHora;
        this.pecas = pecas;
        this.listaDeInspecao = listaDeInspecao;
        this.quantidadeProduzida = quantidadeProduzida;
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

    public Set<Qualidade> getListaDeInspecao() {
        return listaDeInspecao;
    }

    public void setListaDeInspecao(Set<Qualidade> listaDeInspecao) {
        this.listaDeInspecao = listaDeInspecao;
    }

    public Long getQuantidadeProduzida() {
        return quantidadeProduzida;
    }

    public void setQuantidadeProduzida(Long quantidadeProduzida) {
        this.quantidadeProduzida = quantidadeProduzida;
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
        if (!Objects.equals(quantidadeProduzida, producao.quantidadeProduzida))
            return false;
        return Objects.equals(estado, producao.estado);
    }

    @Override
    public int hashCode() {
        int result = idProducao.hashCode();
        result = 31 * result + (dataHora != null ? dataHora.hashCode() : 0);
        result = 31 * result + (pecas != null ? pecas.hashCode() : 0);
        result = 31 * result + (listaDeInspecao != null ? listaDeInspecao.hashCode() : 0);
        result = 31 * result + (quantidadeProduzida != null ? quantidadeProduzida.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    /**
     * Retorna uma representação em formato de String deste objeto.
     * @return Retorna uma representação textual da entidade (Objeto).
     */
    @Override
    public String toString() {
        return "Producao{" +
                "idProducao=" + idProducao +
                ", dataHora=" + dataHora +
                ", pecas=" + pecas +
                ", listaDeInspecao=" + listaDeInspecao +
                ", quantidadeProduzida=" + quantidadeProduzida +
                ", estado='" + estado + '\'' +
                '}';
    }
}
