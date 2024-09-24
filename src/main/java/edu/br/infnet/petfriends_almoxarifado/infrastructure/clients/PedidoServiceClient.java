package edu.br.infnet.petfriends_almoxarifado.infrastructure.clients;

import edu.br.infnet.petfriends_almoxarifado.infrastructure.dto.PedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pedido-service", url = "http://localhost:8081")
public interface PedidoServiceClient {

    @GetMapping("/api/pedidos/{pedidoId}")
    PedidoDTO buscarPedido(@PathVariable("pedidoId") Long pedidoId);
}

