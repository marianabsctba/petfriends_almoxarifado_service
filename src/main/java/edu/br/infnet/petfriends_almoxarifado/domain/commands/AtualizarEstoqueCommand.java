package edu.br.infnet.petfriends_almoxarifado.domain.commands;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class AtualizarEstoqueCommand {

    @TargetAggregateIdentifier
    private Long produtoId;
    private int quantidade;
}
