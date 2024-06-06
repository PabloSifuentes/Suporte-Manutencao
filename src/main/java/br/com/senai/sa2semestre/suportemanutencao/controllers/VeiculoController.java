package br.com.senai.sa2semestre.suportemanutencao.controllers;

import br.com.senai.sa2semestre.suportemanutencao.entities.Veiculo;
import br.com.senai.sa2semestre.suportemanutencao.repositories.VeiculoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoRepositoy veiculoRepositoy;

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
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable String chassi) {
        Optional<Veiculo> veiculoBuscado = veiculoRepositoy.findById(chassi);
        return veiculoBuscado.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
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
     * @param chassi
     * @param veiculosComDadosAtualizados
     * @return se chassi do existingVeiculo for igual veiculoRepositoy, retorna o veiculo indicado (atualizado), se não chassi não encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> upadateVeiculo(@PathVariable String chassi, @RequestBody Veiculo veiculosComDadosAtualizados) {
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable String chassi) {
        Optional<Veiculo> veiculoParaDeletar = veiculoRepositoy.findById(chassi);
        if (veiculoParaDeletar.isPresent()) {
            veiculoRepositoy.delete(veiculoParaDeletar.get());
            return ResponseEntity.noContent().build(); //Conteúdo deletado.
        } else {
            return ResponseEntity.notFound().build(); //ID não encontrado.
        }
    }
}
