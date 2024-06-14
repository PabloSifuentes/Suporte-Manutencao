package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstoque;

    private Long quantidadeDisponivel;

    @ManyToOne
    @JoinColumn(name = "idPecas", referencedColumnName = "idPecas")
    private Pecas pecas;

    public Estoque(){
    }
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

    @Override
    public String toString() {
        return "Estoque{" +
                "idEstoque=" + idEstoque +
                ", quantidadeEstoque=" + quantidadeDisponivel +
                ", pecas=" + pecas +
                '}';
    }
}