package edu.br.infnet.petfriends_almoxarifado.consumers;

import edu.br.infnet.petfriends_almoxarifado.dto.PedidoEventoDto;
import edu.br.infnet.petfriends_almoxarifado.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class PedidoEventConsumer {

    @Autowired
    private EstoqueService estoqueService;

    @RabbitListener(queues = "${broker.queue.pedido.name}")
    public void processarEventoPedido(PedidoEventoDto pedidoEvento) {
        estoqueService.processarEstoque(pedidoEvento);
    }
}
