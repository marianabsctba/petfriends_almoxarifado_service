package edu.br.infnet.petfriends_almoxarifado.interfaces.controller;
import edu.br.infnet.petfriends_almoxarifado.application.PedidoEstoqueService;
import edu.br.infnet.petfriends_almoxarifado.domain.model.Estoque;
import edu.br.infnet.petfriends_almoxarifado.infrastructure.dto.EstoqueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    @Autowired
    private PedidoEstoqueService estoqueService;


    @GetMapping
    public ResponseEntity<List<Estoque>> listarTodos() {
        List<Estoque> estoque = estoqueService.listarTodos();
        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Estoque> buscarPorId(@PathVariable Long id) {
        Optional<Estoque> produto = estoqueService.buscarPorProdutoId(id);
        return produto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Estoque> criarProduto(@RequestBody EstoqueDto request) {
        Estoque produto = estoqueService.criarEstoque(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        estoqueService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}