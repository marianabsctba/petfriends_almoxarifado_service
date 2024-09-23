package edu.br.infnet.petfriends_almoxarifado.interfaces.controller;
import edu.br.infnet.petfriends_almoxarifado.application.service.EstoqueService;
import edu.br.infnet.petfriends_almoxarifado.domain.model.Estoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public ResponseEntity<List<Estoque>> listarTodos() {
        List<Estoque> estoques = estoqueService.listarTodos();
        return ResponseEntity.ok(estoques);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<Estoque> buscarPorProdutoId(@PathVariable Long produtoId) {
        Optional<Estoque> estoque = estoqueService.buscarPorProdutoId(produtoId);
        return estoque.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualizar/{produtoId}")
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable Long produtoId, @RequestParam int quantidade) {
        Estoque estoque = estoqueService.atualizarEstoque(produtoId, quantidade);
        return ResponseEntity.ok(estoque);
    }
}

