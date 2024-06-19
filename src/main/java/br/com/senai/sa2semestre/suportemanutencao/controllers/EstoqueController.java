package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Estoque;
import br.com.senai.sa2semestre.suportemanutencao.entities.Pecas;
import br.com.senai.sa2semestre.suportemanutencao.repositories.EstoqueRepository;
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
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private PecasRepository pecasRepository;
    /**
     * Adiciona todos os itens na lista.
     * @return uma lista de todos os Estoque existentes.
     */
    @GetMapping
    public List<Estoque> getALLEstoque() {
        return estoqueRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     *
     * @param id o ID do item de estoque a ser recuperado.
     * @return uma resposta HTTP contendo o Estoque encontrado, ou uma resposta de "not found"
     * se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Estoque> getEstoqueById(@PathVariable Long id) {
        Optional<Estoque> estoqueBuscado = estoqueRepository.findById(id);
        return estoqueBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repositório (Banco de dados).
     * @param estoque o item de estoque a ser criado.
     * @return o Estoque criado.
     */
    @PostMapping
    public Estoque createEstoque(@RequestBody Estoque estoque) {
        if (estoque.getPecas() != null && estoque.getPecas().getIdPecas() != null) {
            Pecas pecas = pecasRepository.findById(estoque.getPecas().getIdPecas())
                    .orElseThrow(() -> new IllegalArgumentException("Peça não encontrado"));
            estoque.setPecas(pecas);
        }
        return estoqueRepository.save(estoque);
    }

    /**
     * Atualiza um item dentro do Repositório.
     *
     * @param id o ID do item de estoque a ser atualizado.
     * @param estoqueComDadosAtualizados o objeto Estoque contendo os dados atualizados.
     * @return uma resposta HTTP contendo o Estoque atualizado, ou uma resposta de "not found"
     * se o item de estoque não for encontrado.
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
     * @param id o ID do item de estoque a ser deletado.
     * @return uma resposta HTTP indicando o resultado da operação: "no content"
     * se deletado, ou "not found" se o item de estoque não for encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        Optional<Estoque> estoqueParaDeletar = estoqueRepository.findById(id);
        if (estoqueParaDeletar.isPresent()) {
            estoqueRepository.delete(estoqueParaDeletar.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}