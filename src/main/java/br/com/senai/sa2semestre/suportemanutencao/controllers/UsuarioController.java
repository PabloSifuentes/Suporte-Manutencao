package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Usuario;
import br.com.senai.sa2semestre.suportemanutencao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/**
 * @RestController Indica que esta classe é um controlador REST, onde cada método retorna
 * um objeto diferente como uma resposta HTTP.
 * @RequestMapping Define o mapeamento de URL base para este controlador.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * adiciona todos os itens na lista.
     */
    @GetMapping
    public List<Usuario> getALLUsuario() {
        return usuarioRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(id);
        return usuarioBuscado.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repository (Banco de dados).
     */
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Atualiza o item dentro do Repository.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioComDadosAtualizados) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
            usuarioComDadosAtualizados.setIdUsuario(id);
            return ResponseEntity.ok(usuarioRepository.save(usuarioComDadosAtualizados));
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    /**
     * Deleta o item dentro do Repository.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioParaDeletar = usuarioRepository.findById(id);
        if (usuarioParaDeletar.isPresent()) {
            usuarioRepository.delete(usuarioParaDeletar.get());
            return ResponseEntity.noContent().build();   //Conteúdo deletado.
        } else {
            return ResponseEntity.notFound().build();   //ID não encontrado.
        }
    }
}
