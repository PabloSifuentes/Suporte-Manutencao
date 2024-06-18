package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Pecas;
import br.com.senai.sa2semestre.suportemanutencao.entities.Producao;
import br.com.senai.sa2semestre.suportemanutencao.repositories.PecasRepository;
import br.com.senai.sa2semestre.suportemanutencao.repositories.ProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producoes")
public class ProducaoController {

    @Autowired
    private ProducaoRepository producaoRepository;

    @Autowired
    private PecasRepository pecasRepository;

    /**
     * Adiciona todos os itens na lista.
     */
    @GetMapping
    public List<Producao> getALLProducao() {
        return producaoRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producao> getProducaoById(@PathVariable Long id) {
        Optional<Producao> producaoBuscado = producaoRepository.findById(id);
        return producaoBuscado.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repository (Banco de Dados).
     */
    @PostMapping
    public Producao createProducao (@RequestBody Producao producao) {
        if (producao.getPecas() != null && producao.getPecas().getIdPecas() != null) {
            Pecas pecas = pecasRepository.findById(producao.getPecas().getIdPecas())
                    .orElseThrow(() -> new RuntimeException("Peça não encontrada"));
            producao.setPecas(pecas);
        }
        return producaoRepository.save(producao);
    }

    /**
     * Atualiza um item dentro do Repository.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producao> updateProducao(@PathVariable Long id, @RequestBody Producao producaoComDadosAtualizados) {
        Optional<Producao> existingProducao = producaoRepository.findById(id);
        if (existingProducao.isPresent()) {
            producaoComDadosAtualizados.setIdProducao(id);
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
