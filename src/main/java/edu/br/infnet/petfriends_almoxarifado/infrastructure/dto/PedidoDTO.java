package edu.br.infnet.petfriends_almoxarifado.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long pedidoId;
    private Long clienteId;
    private Long produtoId;
    private int quantidade;
    private String enderecoEntrega;
}
