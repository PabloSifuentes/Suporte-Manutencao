package br.com.senai.sa2semestre.suportemanutencao.repositories;

import br.com.senai.sa2semestre.suportemanutencao.entities.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
