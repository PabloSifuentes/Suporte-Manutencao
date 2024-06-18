package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Equipamento;
import br.com.senai.sa2semestre.suportemanutencao.entities.Manutencao;
import br.com.senai.sa2semestre.suportemanutencao.repositories.EquipamentoRepository;
import br.com.senai.sa2semestre.suportemanutencao.repositories.ManutencaoRepository;
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
@RequestMapping("/manutencoes")
public class ManutencaoController {
    
    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;
    /**
     * Adiciona todos os itens na lista.
     */
    @GetMapping
    public List<Manutencao> getAllManutencao() {
        return manutencaoRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Manutencao> getManutencaoById(@PathVariable Long id) {
        Optional<Manutencao> manutencaoBuscado = manutencaoRepository.findById(id);
        return manutencaoBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionado no Repositório (Banco de dados).
     */

    @PostMapping
    public Manutencao createManutencao(@RequestBody Manutencao manutencao) {
        if (manutencao.getEquipamento() != null && manutencao.getEquipamento().getIdEquipamento() != null) {
            Equipamento equipamento = equipamentoRepository.findById(manutencao.getEquipamento().getIdEquipamento())
                    .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));
            manutencao.setEquipamento(equipamento);
        }
        return manutencaoRepository.save(manutencao);
    }
    /**
     * Atualiza um item dentro do Repositório.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Manutencao> upadateManutencao(@PathVariable Long id, @RequestBody Manutencao manutencaoComDadosAtualizados) {
        Optional<Manutencao> existingManutencao = manutencaoRepository.findById(id);
        if (existingManutencao.isPresent()) {
            manutencaoComDadosAtualizados.setIdManutencao(id);
            return ResponseEntity.ok(manutencaoRepository.save(manutencaoComDadosAtualizados));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deleta um item dentro do Repositório.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManutencao(@PathVariable Long id) {
        Optional<Manutencao> manutencaoParaDeletar = manutencaoRepository.findById(id);
        if (manutencaoParaDeletar.isPresent()) {
            manutencaoRepository.delete(manutencaoParaDeletar.get());
            return ResponseEntity.noContent().build(); //Conteúdo deletado.
        } else {
            return ResponseEntity.notFound().build(); //ID não encontrado.
        }
    }
}
