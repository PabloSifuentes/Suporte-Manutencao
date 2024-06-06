package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstoque;

    private Long quantidadeEstoque;

    @ManyToOne
    @JoinColumn(name = "idPecas", referencedColumnName = "idPecas")
    private Pecas pecas;

    public Estoque(){
    }
    public Estoque(Long idEstoque, Long quantidadeEstoque, Pecas pecas) {
        this.idEstoque = idEstoque;
        this.quantidadeEstoque = quantidadeEstoque;
        this.pecas = pecas;
    }
    public Long getIdEstoque(){
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Long getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Long quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
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
        if (!Objects.equals(quantidadeEstoque, estoque.quantidadeEstoque)) return false;
        return Objects.equals(pecas, estoque.pecas);
    }

    @Override
    public int hashCode() {
        int result = idEstoque.hashCode();
        result = 31 * result + (quantidadeEstoque != null ? quantidadeEstoque.hashCode() : 0);
        result = 31 * result + (pecas != null ? pecas.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "idEstoque=" + idEstoque +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", pecas=" + pecas +
                '}';
    }
}