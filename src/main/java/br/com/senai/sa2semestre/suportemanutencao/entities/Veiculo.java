package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.*;

import java.util.*;

/**
 * @Entity Indica que a classe é uma entidade JPA e um objeto que será mapeado para uma tabela no bando de dados .
 * @Table Especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
 */
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Veiculos_Pecas",
            joinColumns = {@JoinColumn(name = "chassi")},
            inverseJoinColumns = {@JoinColumn(name = "idPecas")}
    )
    private List<Pecas> pecas= new ArrayList<>();
    @Id
    @Column(name = "chassi", length = 17, nullable = false, unique = true)
    private String chassi;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "ano")
    private Long ano;

    @Column(name = "cor")
    private String cor;

    /**
     * Construtor Padrão é necessário para a JPA instanciar a entidade.
     */
    public Veiculo() {
    }

    /**
     * Construtor com Parâmetros facilita a criação de instâncias da entidade
     * com valores especificos.
     * @param pecas conjunto de peças associado ao veiculo.
     * @param chassi O numero do chassi do veiculo
     * @param modelo O modelo de veiculo.
     * @param ano O ano do veiculo.
     * @param cor A cor do veiculo.
     */
    public Veiculo(List<Pecas> pecas, String chassi, String modelo, Long ano, String cor) {
        this.pecas = pecas;
        this.chassi = chassi;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
    }

    public List<Pecas> getPecas() {
        return pecas;
    }

    public void setPecas(List<Pecas> pecas) {
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
        if (!chassi.equals(veiculo.chassi)) return false;
        if (!Objects.equals(modelo, veiculo.modelo)) return false;
        if (!Objects.equals(ano, veiculo.ano)) return false;
        return Objects.equals(cor, veiculo.cor);
    }

    @Override
    public int hashCode() {
        int result = pecas != null ? pecas.hashCode() : 0;
        result = 31 * result + chassi.hashCode();
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        result = 31 * result + (ano != null ? ano.hashCode() : 0);
        result = 31 * result + (cor != null ? cor.hashCode() : 0);
        return result;
    }

    /**
     * Retorna uma representação em formato de String deste objeto.
     * @return Retorna uma representação textual da entidade (Objeto).
     */
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
