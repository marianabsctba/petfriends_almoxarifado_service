package edu.br.infnet.petfriends_almoxarifado.domain.aggregate;

import edu.br.infnet.petfriends_almoxarifado.domain.commands.AtualizarEstoqueCommand;
import edu.br.infnet.petfriends_almoxarifado.domain.events.EstoqueAtualizadoEvent;
import edu.br.infnet.petfriends_almoxarifado.domain.vo.Quantidade;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstoqueAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @AggregateIdentifier
    private Long id;

    private String produtoNome;

    @Embedded
    private Quantidade quantidade;

    @CommandHandler
    public EstoqueAggregate(AtualizarEstoqueCommand command) {
        apply(new EstoqueAtualizadoEvent(command.getProdutoId(), command.getQuantidade()));
    }

    @EventSourcingHandler
    public void on(EstoqueAtualizadoEvent event) {
        this.id = event.getProdutoId();
        this.quantidade.subtrair(event.getQuantidade());
    }
}

