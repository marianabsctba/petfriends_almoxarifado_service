package edu.br.infnet.petfriends_almoxarifado.domain.events;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstoqueAtualizadoEvent {
    private Long produtoId;
    private int quantidade;
}
