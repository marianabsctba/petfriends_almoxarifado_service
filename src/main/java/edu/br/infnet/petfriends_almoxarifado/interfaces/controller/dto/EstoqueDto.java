package edu.br.infnet.petfriends_almoxarifado.interfaces.controller.dto;

import lombok.Data;

@Data
public class EstoqueDto {
    private Long produtoId;
    private String nome;
    private String categoria;
    private int quantidade;
}
