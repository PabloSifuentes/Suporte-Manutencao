package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Pecas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPecas;

    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "pecas")
    private List<Estoque> listaDoestoques;

    public Pecas(Long idPecas, String nome, String descricao) {
        this.idPecas = idPecas;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Pecas() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pecas pecas = (Pecas) o;

        if (!idPecas.equals(pecas.idPecas)) return false;
        if (!Objects.equals(nome, pecas.nome)) return false;
        return Objects.equals(descricao, pecas.descricao);
    }

    @Override
    public int hashCode() {
        int result = idPecas.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pecas{" +
                "idPecas=" + idPecas +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
