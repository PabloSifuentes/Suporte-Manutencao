package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Pecas;
import br.com.senai.sa2semestre.suportemanutencao.repositories.PecasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pecas")
public class PecasController {
    @Autowired
    private PecasRepository pecasRepository;

    /**
     * Adicionar todos os itens  na lista.
     */
    @GetMapping
    public List<Pecas> getALLPecas() {
        return pecasRepository.findAll();
    }

    /**
     * Retorna o item respetivo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pecas> getPecasById(@PathVariable Long id) {
        Optional<Pecas> pecasBuscado = pecasRepository.findById(id);
        return pecasBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Criar um item adicionando no Repositório (Banco de dados).
     *
     */
    @PostMapping
    public Pecas createPecas(@RequestBody Pecas pecas) {
        return pecasRepository.save(pecas);
    }

    /**
     * Atualiza um item dentro do Repositório.
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
