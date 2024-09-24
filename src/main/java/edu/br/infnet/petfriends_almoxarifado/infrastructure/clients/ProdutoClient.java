package edu.br.infnet.petfriends_almoxarifado.infrastructure.clients;

import edu.br.infnet.petfriends_almoxarifado.infrastructure.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produto-service", url = "http://localhost:8082") // URL do microserviço de produto
public interface ProdutoClient {

    @GetMapping("/api/produtos/{produtoId}")
    ProdutoDTO buscarProdutoPorId(@PathVariable("produtoId") Long produtoId);
}
