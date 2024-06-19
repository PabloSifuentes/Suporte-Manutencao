package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Producao;
import br.com.senai.sa2semestre.suportemanutencao.entities.Qualidade;
import br.com.senai.sa2semestre.suportemanutencao.repositories.ProducaoRepository;
import br.com.senai.sa2semestre.suportemanutencao.repositories.QualidadeRepository;
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
@RequestMapping("/qualidades")
public class QualidadeController {
    @Autowired
    private QualidadeRepository qualidadeRepository;

    @Autowired
    private ProducaoRepository producaoRepository;

    /**
     * Adiciona todos os itens na lista.
     * @return uma lista de todos as inpeções existentes.
     */
    @GetMapping
    public List<Qualidade> getAllQualidade() {
        return qualidadeRepository.findAll();
    }

    /**
     * Retorna o item pelo respectivo ID.
     *
     * @param id o ID do item de inspeção a ser recuperado.
     * @return uma resposta HTTP contendo a inspeção encontrado, ou uma resposta de "not found"
     * se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Qualidade> getQualidadeById(@PathVariable Long id) {
        Optional<Qualidade> qualidadebuscado = qualidadeRepository.findById(id);
        return qualidadebuscado.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repositório (Banco de dados).
     * @param qualidade o item de estoque a ser criado.
     * @return a inspeção criado.
     */
    @PostMapping
    public Qualidade createQualidade(@RequestBody Qualidade qualidade) {
        if (qualidade.getProducao() != null && qualidade.getProducao().getIdProducao() != null) {
            Producao producao = producaoRepository.findById(qualidade.getProducao().getIdProducao())
                    .orElseThrow(() -> new RuntimeException("Produção não encontrado"));
            qualidade.setProducao(producao);
        }
        return qualidadeRepository.save(qualidade);
    }

    /**
     * Atualiza um item dentro do Repositório.
     *
     * @param id o ID do item de inspeção a ser atualizado.
     * @param qualidadeComDadosAtualizados o objeto qualidade contendo os dados atualizados.
     * @return uma resposta HTTP contendo o Estoque atualizado, ou uma resposta de "not found"
     * se o item de estoque não for encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Qualidade> updateQualidade(@PathVariable Long id, @RequestBody Qualidade qualidadeComDadosAtualizados) {
        Optional<Qualidade> existingQualidade = qualidadeRepository.findById(id);
        if (existingQualidade.isPresent()) {
            qualidadeComDadosAtualizados.setIdInspecao(id);
            return ResponseEntity.ok(qualidadeRepository.save(qualidadeComDadosAtualizados));
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Deleta um item dentro do Repositório.
     * @param id o ID do item de inspeção a ser deletado.
     * @return uma resposta HTTP indicando o resultado da operação: "no content"
     * se deletado, ou "not found" se o item de inspeção não for encontrado.
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
