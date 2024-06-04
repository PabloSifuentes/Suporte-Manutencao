package br.com.senai.sa2semestre.suportemanutencao.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

public class veiculosPecas {

    private String chassi;
@ManyToMany
@JoinColumn(name = "idPecas", referencedColumnName = "idPecas")
    private Pecas pecas;

@ManyToMany
@JoinColumn(name = "idVeiculo", referencedColumnName = "idVeiculo")
private Veiculo veiculo;

    private Long quantidade;
}
