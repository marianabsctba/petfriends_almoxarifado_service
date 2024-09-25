package edu.br.infnet.petfriends_almoxarifado.application;

import edu.br.infnet.petfriends_almoxarifado.infrastructure.dto.PedidoEventoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class PedidoEventConsumer {

    @Autowired
    private PedidoEstoqueService estoqueService;

    @RabbitListener(queues = "${broker.queue.pedido.name}")
    public void processarEventoPedido(PedidoEventoDto pedidoEvento) {
        estoqueService.processarEstoque(pedidoEvento);
    }
}