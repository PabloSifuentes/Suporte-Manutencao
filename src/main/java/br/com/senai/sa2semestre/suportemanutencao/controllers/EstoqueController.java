package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Estoque;
import br.com.senai.sa2semestre.suportemanutencao.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueRepository estoqueRepository;

    /**
     * Adiciona todos os itens na lista.
     */
    @GetMapping
    public List<Estoque> getALLEstoque() {
        return estoqueRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Estoque> getEstoqueById(@PathVariable Long id) {
        Optional<Estoque> estoqueBuscado = estoqueRepository.findById(id);
        return estoqueBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repositório (Banco de dados).
     */
    @PostMapping
    public Estoque createEstoque(@RequestBody Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    /**
     * Atualiza um item dentro do Repositório.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Estoque> UpdateEstoque(@PathVariable Long id, @RequestBody Estoque estoqueComDadosAtualizados) {
        Optional<Estoque> existingEstoque = estoqueRepository.findById(id);
        if (existingEstoque.isPresent()) {
            estoqueComDadosAtualizados.setIdEstoque(id);
            return ResponseEntity.ok(estoqueRepository.save(estoqueComDadosAtualizados));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deleta um item dentro do Repositório.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        Optional<Estoque> estoqueParaDeletar = estoqueRepository.findById(id);
        if (estoqueParaDeletar.isPresent()) {
            estoqueRepository.delete(estoqueParaDeletar.get());
            return ResponseEntity.noContent().build(); //Conteúdo deletado.
        } else {
            return ResponseEntity.notFound().build(); //ID não encontrado.
        }
    }
}