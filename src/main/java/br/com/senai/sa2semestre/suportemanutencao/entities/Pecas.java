package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Pecas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPecas;

    private String nome;

    private String descricao;

   private int quantidade;

   @ManyToMany(mappedBy = "pecas")
   private Set<Veiculo> veiculos = new HashSet<>();

    public Pecas() {
    }

    public Pecas(Long idPecas, String nome, String descricao, int quantidade, Set<Veiculo> veiculos) {
        this.idPecas = idPecas;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.veiculos = veiculos;
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

    public Set<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Set<Veiculo> veiculos) {
        this.veiculos = veiculos;
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
        return Objects.equals(veiculos, pecas.veiculos);
    }

    @Override
    public int hashCode() {
        int result = idPecas.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + quantidade;
        result = 31 * result + (veiculos != null ? veiculos.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pecas{" +
                "idPecas=" + idPecas +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", veiculos=" + veiculos +
                '}';
    }
}
