package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Pecas;
import br.com.senai.sa2semestre.suportemanutencao.repositories.PecasRepository;
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
@RequestMapping("/pecas")
public class PecasController {
    @Autowired
    private PecasRepository pecasRepository;

    /**
     * Adiciona todos os itens na lista.
     * @return uma lista de todos os pecas existentes.
     */
    @GetMapping
    public List<Pecas> getALLPecas() {
        return pecasRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     *
     * @param id o ID do item de peças a ser recuperado.
     * @return uma resposta HTTP contendo o peças encontrado, ou uma resposta de "not found"
     * se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pecas> getPecasById(@PathVariable Long id) {
        Optional<Pecas> pecasBuscado = pecasRepository.findById(id);
        return pecasBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repositório (Banco de dados).
     * @param pecas o item de peças a ser criado.
     * @return o peças criado.
     */
    @PostMapping
    public ResponseEntity<Pecas> createPecas(@RequestBody Pecas pecas) {
        Pecas pecaSalva = pecasRepository.save(pecas);
        return ResponseEntity.ok(pecaSalva);
    }

    /**
     * Atualiza um item dentro do Repositório.
     *
     * @param id o ID do item de peças a ser atualizado.
     * @param pecasComDadosAtualizados o objeto peças contendo os dados atualizados.
     * @return uma resposta HTTP contendo o peças atualizado, ou uma resposta de "not found"
     * se o item de peças não for encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pecas> updatePecas(@PathVariable Long id, @RequestBody Pecas pecasComDadosAtualizados) {
        Optional<Pecas> existingPecas = pecasRepository.findById(id);
        if (existingPecas.isPresent()) {
            pecasComDadosAtualizados.setIdPecas(id);
            return ResponseEntity.ok(pecasRepository.save(pecasComDadosAtualizados));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deleta um item dentro do Repositório.
     * @param id o ID do item de peças a ser deletado.
     * @return uma resposta HTTP indicando o resultado da operação: "no content"
     * se deletado, ou "not found" se o item de peças não for encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPecas(@PathVariable Long id) {
        Optional<Pecas> pecasParaDeletar = pecasRepository.findById(id);
        if (pecasParaDeletar.isPresent()) {
            pecasRepository.delete(pecasParaDeletar.get());
            return ResponseEntity.noContent().build(); //Conteúdo deletado.
        } else {
            return ResponseEntity.notFound().build(); //ID não encontrado.
        }
    }
}
