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
/**
 * @RestController Indica que esta classe é um controlador REST, onde cada método retorna
 * um objeto diferente como uma resposta HTTP.
 * @RequestMapping Define o mapeamento de URL base para este controlador.
 */
@RestController
@RequestMapping("/producoes")
public class ProducaoController {

    @Autowired
    private ProducaoRepository producaoRepository;

    @Autowired
    private PecasRepository pecasRepository;

    /**
     * Adiciona todos os itens na lista.
     * @return uma lista de todos as produções existentes.
     */
    @GetMapping
    public List<Producao> getALLProducao() {
        return producaoRepository.findAll();
    }

    /**
     * Retorna o ‘item’ pelo respectivo ID.
     *
     * @param id o ID do ‘item’ da produção a ser recuperado.
     * @return uma resposta HTTP contendo a produção encontrado, ou uma resposta de "not found"
     * se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producao> getProducaoById(@PathVariable Long id) {
        Optional<Producao> producaoBuscado = producaoRepository.findById(id);
        return producaoBuscado.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    /**
     * Cria um item adicionando no Repositório (Banco de dados).
     * @param producao o item de produção a ser criado.
     * @return a produção criado.
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
     * Atualiza um item dentro do Repositório.
     *
     * @param id o ID do item de produção a ser atualizado.
     * @param producaoComDadosAtualizados o objeto produção contendo os dados atualizados.
     * @return uma resposta HTTP contendo a produção atualizado, ou uma resposta de "not found"
     * se o item de produção não for encontrado.
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
     * Deleta um item dentro do Repositório.
     * @param id o ID do item de produção a ser deletado.
     * @return uma resposta HTTP indicando o resultado da operação: "no content"
     * se deletado, ou "not found" se o item de produção não for encontrado.
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
