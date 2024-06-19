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
     * Adiciona todos os itens na lista.
     * @return uma lista de todos os usuario existentes.
     */
    @GetMapping
    public List<Usuario> getALLUsuario() {
        return usuarioRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     *
     * @param id o ID do item do usuario a ser recuperado.
     * @return uma resposta HTTP contendo o usuario encontrado, ou uma resposta de "not found"
     * se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(id);
        return usuarioBuscado.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repositório (Banco de dados).
     * @param usuario o item do usuario a ser criado.
     * @return o usuario criado.
     */
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Atualiza um item dentro do Repositório.
     *
     * @param id o ID do item de usuario a ser atualizado.
     * @param usuarioComDadosAtualizados o objeto usuario contendo os dados atualizados.
     * @return uma resposta HTTP contendo o usuario atualizado, ou uma resposta de "not found"
     * se o item do usuario não for encontrado.
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
     * Deleta um item dentro do Repositório.
     * @param id o ID do item do usuario a ser deletado.
     * @return uma resposta HTTP indicando o resultado da operação: "no content"
     * se deletado, ou "not found" se o item do usuario não for encontrado.
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
