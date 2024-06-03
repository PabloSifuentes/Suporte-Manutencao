package br.com.senai.sa2semestre.suportemanutencao.repositories;

import br.com.senai.sa2semestre.suportemanutencao.entities.Producao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducaoRepository extends JpaRepository<Producao, Long> {
}
