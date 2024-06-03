package br.com.senai.sa2semestre.suportemanutencao.repositories;

import br.com.senai.sa2semestre.suportemanutencao.entities.Pecas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PecasRepository extends JpaRepository<Pecas, Long> {
}
