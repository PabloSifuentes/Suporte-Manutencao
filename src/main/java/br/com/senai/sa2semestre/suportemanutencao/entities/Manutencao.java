package br.com.senai.sa2semestre.suportemanutencao.entities;


import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Entity Indica que a classe é uma entidade JPA e um objeto que será mapeado para uma tabela no banco de dados.
 * @Table Especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
 */
@Entity
@Table(name = "manutencao")
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManutencao;

    @ManyToOne
    @JoinColumn(name = "idEquipamento", referencedColumnName = "idEquipamento")
    private Equipamento equipamento;

    @Column(name = "dataHoraInicio")
    private LocalDateTime dataHoraInicio;

    @Column(name = "dataHoraFim")
    private LocalDateTime dataHoraFim;

    @Column(nullable = false)
    private String descricaoServico;

    @Column(name = "estado")
    private String estado;

    /**
     * Construtor Padrão é necessário para a JPA instanciar a entidade.
     */
    public Manutencao(){
    }

    /**
     * Construtor com Parâmetros facilita a criação de instâncias da entidade
     * com valores especificos.
     * @param idManutencao O identificador único da manutencao.
     * @param equipamento O equipamento associado há manutencao.
     * @param dataHoraInicio A data e hora de início da manutencao.
     * @param dataHoraFim A data e hora de término da manutencao.
     * @param descricaoServico A descrição do serviço que será realizado durante a manutencao.
     * @param estado O estado atual que se encontra a manutencao.
     */
    public Manutencao(Long idManutencao, Equipamento equipamento, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String descricaoServico, String estado) {
        this.idManutencao = idManutencao;
        this.equipamento = equipamento;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.descricaoServico = descricaoServico;
        this.estado = estado;
    }

    public Long getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(Long idManutencao) {
        this.idManutencao = idManutencao;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manutencao that = (Manutencao) o;

        if (!idManutencao.equals(that.idManutencao)) return false;
        if (!Objects.equals(equipamento, that.equipamento)) return false;
        if (!Objects.equals(dataHoraInicio, that.dataHoraInicio))
            return false;
        if (!Objects.equals(dataHoraFim, that.dataHoraFim)) return false;
        if (!Objects.equals(descricaoServico, that.descricaoServico))
            return false;
        return Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        int result = idManutencao.hashCode();
        result = 31 * result + (equipamento != null ? equipamento.hashCode() : 0);
        result = 31 * result + (dataHoraInicio != null ? dataHoraInicio.hashCode() : 0);
        result = 31 * result + (dataHoraFim != null ? dataHoraFim.hashCode() : 0);
        result = 31 * result + (descricaoServico != null ? descricaoServico.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    /**
     * Retorna uma representação em formato de String deste objeto.
     * @return Retorna uma representação textual da entidade (Objeto).
     */
    @Override
    public String toString() {
        return "Manutencao{" +
                "idManutencao=" + idManutencao +
                ", equipamento=" + equipamento +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", descricaoServico='" + descricaoServico + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}