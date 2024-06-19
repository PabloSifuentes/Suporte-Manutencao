package br.com.senai.sa2semestre.suportemanutencao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

/**
 * @Entity Indica que a classe é uma entidade JPA e um objeto que será mapeado para uma tabela no banco de dados.
 * @Table Especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
 */
@Entity
@Table(name = "pecas")
public class Pecas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPecas;

    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "quantidade")
   private int quantidade;

   @ManyToMany(mappedBy = "pecas", cascade = CascadeType.ALL)
   @JsonIgnore
   private List<Veiculo> listaDeVeiculo = new ArrayList<>();

   @OneToMany(mappedBy = "pecas", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonIgnore
   private List<Producao> listaDeProducao = new ArrayList<>();

   @OneToMany(mappedBy = "pecas", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonIgnore
   private List<Estoque> listaDeEstoque = new ArrayList<>();

    /**
     * Construtor Padrão é necessário para a JPA instanciar a entidade.
     */
    public Pecas() {
    }

    /**
     * Construtor com Parâmetros facilita a criação de instâncias da entidade
     *  com valores especificos.
     * @param idPecas O identificador único de peças.
     * @param nome O nome da peça.
     * @param descricao A descrição de peças.
     * @param quantidade A quantidade de peças.
     * @param listaDeVeiculo Lista de veiculos que usam essa peça.
     * @param listaDeProducao Lista de produções que usam essa peça.
     * @param listaDeEstoque Lista de Estoques que possuem essa peça.
     */
    public Pecas(Long idPecas, String nome, String descricao, int quantidade, List<Veiculo> listaDeVeiculo, List<Producao> listaDeProducao, List<Estoque> listaDeEstoque) {
        this.idPecas = idPecas;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.listaDeVeiculo = listaDeVeiculo;
        this.listaDeProducao = listaDeProducao;
        this.listaDeEstoque = listaDeEstoque;
    }

    public Long getIdPecas() {
        return idPecas;
    }

    public void setIdPecas(Long idPecas) {
        this.idPecas = idPecas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<Veiculo> getListaDeVeiculo() {
        return listaDeVeiculo;
    }

    public void setListaDeVeiculo(List<Veiculo> listaDeVeiculo) {
        this.listaDeVeiculo = listaDeVeiculo;
    }

    public List<Producao> getListaDeProducao() {
        return listaDeProducao;
    }

    public void setListaDeProducao(List<Producao> listaDeProducao) {
        this.listaDeProducao = listaDeProducao;
    }

    public List<Estoque> getListaDeEstoque() {
        return listaDeEstoque;
    }

    public void setListaDeEstoque(List<Estoque> listaDeEstoque) {
        this.listaDeEstoque = listaDeEstoque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pecas pecas = (Pecas) o;

        if (quantidade != pecas.quantidade) return false;
        if (!idPecas.equals(pecas.idPecas)) return false;
        if (!Objects.equals(nome, pecas.nome)) return false;
        if (!Objects.equals(descricao, pecas.descricao)) return false;
        if (!Objects.equals(listaDeVeiculo, pecas.listaDeVeiculo))
            return false;
        if (!Objects.equals(listaDeProducao, pecas.listaDeProducao))
            return false;
        return Objects.equals(listaDeEstoque, pecas.listaDeEstoque);
    }

    @Override
    public int hashCode() {
        int result = idPecas.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + quantidade;
        result = 31 * result + (listaDeVeiculo != null ? listaDeVeiculo.hashCode() : 0);
        result = 31 * result + (listaDeProducao != null ? listaDeProducao.hashCode() : 0);
        result = 31 * result + (listaDeEstoque != null ? listaDeEstoque.hashCode() : 0);
        return result;
    }

    /**
     * Retorna uma representação em formato de String deste objeto.
     * @return Retorna uma representação textual da entidade (Objeto).
     */
    @Override
    public String toString() {
        return "Pecas{" +
                "idPecas=" + idPecas +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", listaDeVeiculo=" + listaDeVeiculo +
                ", listaDeProducao=" + listaDeProducao +
                ", listaDeEstoque=" + listaDeEstoque +
                '}';
    }
}
