package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Entity Indica que a classe é uma entidade JPA e um objeto que será mapeado para uma tabela no banco de dados.
 * @Table Especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
 */
@Entity
@Table(name = "qualidade")
public class Qualidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInspecao;
    @ManyToOne
    @JoinColumn(name = "idProducao", referencedColumnName = "idProducao")
    private Producao producao;

    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @Column(name = "resultado")
    private String resultado;

    @Column(name = "comentarios")
    private String comentarios;

    /**
     * Construtor Padrão é necessário para a JPA instanciar a entidade.
     */
    public Qualidade(){
    }

    /**
     * Construtor com Parâmetros facilita a criação de instâncias da entidade
     * com valores especificos.
     * @param idInspecao O identificador único de inspeção da qualidade.
     * @param producao A produção associado a qualidade.
     * @param dataHora A data e hora da inspeção.
     * @param resultado O resultado da inspeção.
     * @param comentarios Comentários adicionais sobre a inspeção.
     */
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

    /**
     * Retorna uma representação em formato de String deste objeto.
     * @return Retorna uma representação textual da entidade (Objeto).
     */
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
