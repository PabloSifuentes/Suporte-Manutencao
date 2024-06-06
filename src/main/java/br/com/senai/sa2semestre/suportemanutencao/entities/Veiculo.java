package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
public class Veiculo {

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Veiculos_Pecas",
            joinColumns = {@JoinColumn(name = "chassi")},
            inverseJoinColumns = {@JoinColumn(name = "idPecas")}
    )
    Set<Pecas> pecas = new HashSet<>();
    @Id
    private String chassi;

    private String modelo;

    private Long ano;

    private String cor;

    public Veiculo() {
    }

    public Veiculo(Set<Pecas> pecas, String chassi, String modelo, Long ano, String cor) {
        this.pecas = pecas;
        this.chassi = chassi;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
    }
    public Set<Pecas> getPecas() {
        return pecas;
    }

    public void setPecas(Set<Pecas> pecas) {
        this.pecas = pecas;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Veiculo veiculo = (Veiculo) o;

        if (!Objects.equals(pecas, veiculo.pecas)) return false;
        if (!Objects.equals(chassi, veiculo.chassi)) return false;
        if (!Objects.equals(modelo, veiculo.modelo)) return false;
        if (!Objects.equals(ano, veiculo.ano)) return false;
        return Objects.equals(cor, veiculo.cor);
    }

    @Override
    public int hashCode() {
        int result = pecas != null ? pecas.hashCode() : 0;
        result = 31 * result + (chassi != null ? chassi.hashCode() : 0);
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        result = 31 * result + (ano != null ? ano.hashCode() : 0);
        result = 31 * result + (cor != null ? cor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "pecas=" + pecas +
                ", chassi='" + chassi + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", cor='" + cor + '\'' +
                '}';
    }
}
