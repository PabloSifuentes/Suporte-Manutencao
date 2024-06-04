package br.com.senai.sa2semestre.suportemanutencao.repositories;

import br.com.senai.sa2semestre.suportemanutencao.entities.Qualidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualidadeRepository extends JpaRepository<Qualidade, Long> {
}
