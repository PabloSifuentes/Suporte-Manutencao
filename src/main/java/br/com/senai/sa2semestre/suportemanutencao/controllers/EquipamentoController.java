package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Equipamento;
import br.com.senai.sa2semestre.suportemanutencao.repositories.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    /**
     * Adiciona todos os itens na lista.
     */
    @GetMapping
    public List<Equipamento> getAllEquipamentos() {
        return equipamentoRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> getEquipamentoById(@PathVariable Long idParaBusca) {
        Optional<Equipamento> equipamentoBuscado = equipamentoRepository.findById(idParaBusca);
        return equipamentoBuscado.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repositório (Banco de dados).
     */
    @PostMapping
    public Equipamento createEquipamento(@RequestBody Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    /**
     * Atualiza um item dentro do Repositório.
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
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable Long idParaDeletar) {
        Optional<Equipamento> equipamentoParaDeletar = equipamentoRepository.findById(idParaDeletar);
        if (equipamentoParaDeletar.isPresent()) {
            equipamentoRepository.delete(equipamentoParaDeletar.get());
            return ResponseEntity.noContent().build(); // Conteúdo deletado.
        }else{
            return ResponseEntity.notFound().build(); // ID não encontrado.
        }
    }
}