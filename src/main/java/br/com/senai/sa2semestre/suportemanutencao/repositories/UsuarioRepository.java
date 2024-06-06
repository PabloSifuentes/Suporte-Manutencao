package br.com.senai.sa2semestre.suportemanutencao.repositories;

import br.com.senai.sa2semestre.suportemanutencao.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
