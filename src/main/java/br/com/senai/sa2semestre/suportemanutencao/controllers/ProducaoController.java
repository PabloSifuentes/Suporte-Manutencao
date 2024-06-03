package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Producao;
import br.com.senai.sa2semestre.suportemanutencao.repositories.ProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producoes")
public class ProducaoController {
    /**
     * Adiciona todos os itens na lista.
     */
    @Autowired
    private ProducaoRepository producaoRepository;

    /**
     * Retorna o item pelo respectivo ID.
     */
    @GetMapping
    public List<Producao> getALLProducao() {
        return producaoRepository.findAll();
    }

    /**
     * Cria um item adicionando no Repository (Banco de dados).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producao> getProducaoById(@PathVariable Long id) {
        Optional<Producao> producaoBuscado = producaoRepository.findById(id);
        return producaoBuscado.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    /**
     * Atualiza o item dentro do Repository.
     */
    @PostMapping
    public Producao createProducao(@RequestBody Producao producao) {
        return producaoRepository.save(producao);
    }

    /**
     * Deletar um item dentro do Repository.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producao> updateProducao(@PathVariable Long id, @RequestBody Producao producaoComDadosAtualizados) {
        Optional<Producao> existingProducao = producaoRepository.findById(id);
        if (existingProducao.isPresent()) {
            return ResponseEntity.ok(producaoRepository.save(producaoComDadosAtualizados));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Deleta um item dentro do Repository.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProducao(@PathVariable Long id) {
        Optional<Producao> producaoParaDeletar = producaoRepository.findById(id);
        if (producaoParaDeletar.isPresent()) {
            producaoRepository.delete(producaoParaDeletar.get());
            return ResponseEntity.noContent().build();  //Conteúdo deletado.
        }else {
            return ResponseEntity.notFound().build();  //ID não encontrado.
        }
    }
}
