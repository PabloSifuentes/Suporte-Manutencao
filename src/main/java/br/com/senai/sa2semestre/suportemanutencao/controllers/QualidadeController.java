package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Qualidade;
import br.com.senai.sa2semestre.suportemanutencao.repositories.QualidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qualidades")
public class QualidadeController {
@Autowired
    private QualidadeRepository qualidadeRepository;

    /**
     * Adiciona todos os itns na lista.
     */
    @GetMapping
    public List<Qualidade> getAllQualidade() {
        return qualidadeRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Qualidade> getQualidadeById(@PathVariable Long id) {
        Optional<Qualidade> qualidadebuscado = qualidadeRepository.findById(id);
        return qualidadebuscado.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repository (Banco de dados).
     */
    @PostMapping
    public Qualidade createQualidade(@RequestBody Qualidade qualidade) {
        return qualidadeRepository.save(qualidade);
    }

    /**
     * Atualiza um item dentro do Repository.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Qualidade> updateQualidade(@PathVariable Long id, @RequestBody Qualidade qualidadeComDadosAtualizados) {
        Optional<Qualidade> existingQualidade = qualidadeRepository.findById(id);
        if (existingQualidade.isPresent()) {
            return ResponseEntity.ok(qualidadeRepository.save(qualidadeComDadosAtualizados));
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Deleta um item dentro do Repository.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQualidade(@PathVariable Long id) {
        Optional<Qualidade> qualidadeParaDeletar = qualidadeRepository.findById(id);
        if (qualidadeParaDeletar.isPresent()) {
            qualidadeRepository.delete(qualidadeParaDeletar.get());
            return ResponseEntity.noContent().build(); //Conteúdo deletado.
        } else {
            return ResponseEntity.notFound().build(); //ID não encontrado.
        }
    }
}
