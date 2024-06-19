package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Equipamento;
import br.com.senai.sa2semestre.suportemanutencao.repositories.EquipamentoRepository;
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
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    /**
     * Adiciona todos os itens na lista.
     * @return Uma lista de todos os Equipamento existente.
     */
    @GetMapping
    public List<Equipamento> getAllEquipamentos() {
        return equipamentoRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     * @param id  o ID do equipamento a ser recuperado.
     * @return Uma resposta HTTP contendo o Equipamento encontrado, ou uma
     * resposta de "not found" se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> getEquipamentoById(@PathVariable Long id) {
        Optional<Equipamento> equipamentoBuscado = equipamentoRepository.findById(id);
        return equipamentoBuscado.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repositório (Banco de dados).
     * @param equipamento  O equipamento a ser criado.
     * @return o Equipamento criado.
     */
    @PostMapping
    public Equipamento createEquipamento(@RequestBody Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    /**
     * Atualiza um item dentro do Repositório.
     *
     * @param id O ID equipamento a ser atualizado.
     * @param equipamentoComDadosAtualizados O objeto Equipamento contendo os dados atualizados.
     * @return Uma resposta HTTP contendo o Equipamento atualizado, ou uma resposta de "not foud"
     * se o equipamento não for encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> upadateEquipamento(@PathVariable Long id,
                                                          @RequestBody Equipamento equipamentoComDadosAtualizados) {
        Optional<Equipamento> existingEquipamento = equipamentoRepository.findById(id);
        if (existingEquipamento.isPresent()) {
            equipamentoComDadosAtualizados.setIdEquipamento(id);
            return ResponseEntity.ok(equipamentoRepository.save(equipamentoComDadosAtualizados));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deleta um item dentro do Repositório.
     *
     * @param id O ID do equipamento a ser deletado.
     * @return Uma resposta HTTP indicando o resultado da operação: "no content" se deletado, ou
     * "not found" se o equipamento não for encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable Long id) {
        Optional<Equipamento> equipamentoParaDeletar = equipamentoRepository.findById(id);
        if (equipamentoParaDeletar.isPresent()) {
            equipamentoRepository.delete(equipamentoParaDeletar.get());
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}