package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Veiculo;
import br.com.senai.sa2semestre.suportemanutencao.repositories.PecasRepository;
import br.com.senai.sa2semestre.suportemanutencao.repositories.VeiculoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @RestController Indica que esta classe é um controlador REST, onde cada método retorna
 * um objeto diferente como uma resposta HTTP.
 * @RequestMapping Define o mapeamento de URL base para este controlador.
 */
@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoRepositoy veiculoRepositoy;

    @Autowired
    private PecasRepository pecasRepository;

    /**
     * Adiciona todos os itens na lista.
     * @return rertona-rá a lista de Veiculos.
     */
    @GetMapping
    public List<Veiculo> getALLVeiculo() {
        return veiculoRepositoy.findAll();
    }

    /**
     * Retorna o item pelo respectivo Chassi.
     * @param chassi
     * @return irá retornar através do chassi indicado, o tipo de veiculo.
     */


    String regex = "[A-Za-z0-9]+";
    @GetMapping("/{chassi}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable String chassi) {
        if (!Pattern.matches(regex, chassi)) {
            return ResponseEntity.badRequest().build(); // Retorna erro 400 se o chassi for inválido
        } else {
            Optional<Veiculo> veiculoBuscado = veiculoRepositoy.findById(chassi);
            return veiculoBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        }
    /**
     * Cria um item adicionando no Respository (Bnaco de dados).
     * @param veiculo
     * @return retorna o veiculo criado (salvo).
     */
    @PostMapping
    public Veiculo createVeiculo (@RequestBody Veiculo veiculo) {
        return veiculoRepositoy.save(veiculo);
    }

    /**
     * Atualizar um item dentro do Repository.
     *
     * @param chassi
     * @param veiculosComDadosAtualizados
     * @return se chassi do existingVeiculo for igual veiculoRepositoy, retorna o veiculo indicado (atualizado),
     * se não chassi não encontrado.
     */
    @PutMapping("/{chassi}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable String chassi, @RequestBody Veiculo veiculosComDadosAtualizados) {
        if (!Pattern.matches(regex, chassi)) {
            return ResponseEntity.badRequest().build(); // Retorna erro 400 se o chassi for inválido
        }
            Optional<Veiculo> existingVeiculo = veiculoRepositoy.findById(chassi);
            if (existingVeiculo.isPresent()) {
                veiculosComDadosAtualizados.setChassi(chassi);
                return ResponseEntity.ok(veiculoRepositoy.save(veiculosComDadosAtualizados));
            } else {
                return ResponseEntity.notFound().build();
            }
    }
        /**
         * Deleta um item dentro do Repository.
         * @param chassi
         * @return retorna o chassi indicado para deletar, se não chassi não encontrado.
         */
        @DeleteMapping("/{chassi}")
        public ResponseEntity<Void> deletarVeiculo(@PathVariable String chassi) {
            if (!Pattern.matches(regex, chassi)) {
                return ResponseEntity.badRequest().build(); // Retorna erro 400 se o chassi for inválido
            }
            Optional<Veiculo> veiculoParaDeletar = veiculoRepositoy.findById(chassi);
            if (veiculoParaDeletar.isPresent()) {
                veiculoRepositoy.delete(veiculoParaDeletar.get());
                return ResponseEntity.noContent().build(); // Conteúdo deletado.
            } else {
                return ResponseEntity.notFound().build(); // Chassi não encontrado.
            }
        }
}
