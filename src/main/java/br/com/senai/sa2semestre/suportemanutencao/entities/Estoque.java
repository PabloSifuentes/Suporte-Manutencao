package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * @Entity Indica que a classe é uma entidade JPA e um objeto que será mapeado para uma tabela no banco de dados.
 * @Table Especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
 */
@Entity
@Table(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstoque;
    @Column(name = "quantidadeDisponivel")
    private Long quantidadeDisponivel;
    @ManyToOne
    @JoinColumn(name = "idPecas", referencedColumnName = "idPecas")
    private Pecas pecas;

    /**
     * Construtor Padrão é necessário para a JPA instanciar a entidade.
     */
    public Estoque(){
    }

    /**
     * Construtor com Parâmetros facilita a criação de instâncias da entidade
     * com valores especificos.
     * @param idEstoque O identificador único do estoque.
     * @param quantidadeEstoque A quantidade disponível em estoque.
     * @param pecas A peça associada ao estoque.
     */
    public Estoque(Long idEstoque, Long quantidadeEstoque, Pecas pecas) {
        this.idEstoque = idEstoque;
        this.quantidadeDisponivel = quantidadeEstoque;
        this.pecas = pecas;
    }
    public Long getIdEstoque(){
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Long getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Long quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Pecas getPecas() {
        return pecas;
    }

    public void setPecas(Pecas pecas) {
        this.pecas = pecas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estoque estoque = (Estoque) o;

        if (!idEstoque.equals(estoque.idEstoque)) return false;
        if (!Objects.equals(quantidadeDisponivel, estoque.quantidadeDisponivel)) return false;
        return Objects.equals(pecas, estoque.pecas);
    }

    @Override
    public int hashCode() {
        int result = idEstoque.hashCode();
        result = 31 * result + (quantidadeDisponivel != null ? quantidadeDisponivel.hashCode() : 0);
        result = 31 * result + (pecas != null ? pecas.hashCode() : 0);
        return result;
    }

    /**
     * Retorna uma representação em formato de String deste objeto.
     * @return Retorna uma representação textual da entidade (Objeto).
     */
    @Override
    public String toString() {
        return "Estoque{" +
                "idEstoque=" + idEstoque +
                ", quantidadeEstoque=" + quantidadeDisponivel +
                ", pecas=" + pecas +
                '}';
    }
}