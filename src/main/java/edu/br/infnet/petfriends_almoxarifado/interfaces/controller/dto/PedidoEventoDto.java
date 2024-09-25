package edu.br.infnet.petfriends_almoxarifado.interfaces.controller.dto;

import lombok.Data;


@Data
public class PedidoEventoDto {
    private Long produtoId;
    private Long pedidoId;
    private int quantidade;

}