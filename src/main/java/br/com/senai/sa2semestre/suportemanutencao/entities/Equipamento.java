package br.com.senai.sa2semestre.suportemanutencao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

/**
 * @Entity Indica que a classe é uma entidade JPA e um objeto que será mapeado para uma tabela no banco de dados.
 * @Table Especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
 */
@Entity
@Table(name = "equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipamento")
    private Long idEquipamento;
    @Column(name = "tipoDeEquipamento")
    private String tipoDeEquipamento;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Manutencao> listaDeManutencoes = new ArrayList<>();

    /**
     * Construtor Padrão é necessário para a JPA instanciar a entidade.
     */
    public Equipamento() {
    }

    /**
     * Construtor com Parâmetros facilita a criação de instâncias da entidade
     * com valores especificos.
     * @param idEquipamento O identificador único do equipamento.
     * @param tipoDeEquipamento O tipo do equipamento.
     * @param descricao A descrição do equipamento.
     * @param estado O estado atual do equipamento.
     * @param listaDeManutencoes O conjunto de manutenções associadas ao equipamento.
     */
    public Equipamento(Long idEquipamento, String tipoDeEquipamento, String descricao, String estado, List<Manutencao> listaDeManutencoes) {
        this.idEquipamento = idEquipamento;
        this.tipoDeEquipamento = tipoDeEquipamento;
        this.descricao = descricao;
        this.estado = estado;
        this.listaDeManutencoes = listaDeManutencoes;
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

    /**
     * Retorna uma representação em formato de String deste objeto.
     * @return Retorna uma representação textual da entidade (Objeto).
     */
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