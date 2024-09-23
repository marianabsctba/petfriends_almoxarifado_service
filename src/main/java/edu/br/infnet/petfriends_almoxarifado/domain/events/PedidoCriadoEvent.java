package edu.br.infnet.petfriends_almoxarifado.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoCriadoEvent {
    private Long pedidoId;
    private Long clienteId;
    private Long produtoId;
    private String enderecoEntrega;
}

