package edu.br.infnet.petfriends_almoxarifado.application;

import edu.br.infnet.petfriends_almoxarifado.domain.commands.AtualizarEstoqueCommand;
import edu.br.infnet.petfriends_almoxarifado.domain.events.PedidoCriadoEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
public class PedidoEventHandler {

    @Autowired
    private CommandGateway commandGateway;

    @RabbitListener(queues = "${broker.queue.pedido.name}")
    public void processarEventoPedido(PedidoCriadoEvent event) {
        AtualizarEstoqueCommand command = new AtualizarEstoqueCommand(
                event.getProdutoId(),
                event.getQuantidade()
        );
        commandGateway.send(command);
    }
}
