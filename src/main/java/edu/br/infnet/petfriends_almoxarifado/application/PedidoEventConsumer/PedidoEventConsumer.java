package edu.br.infnet.petfriends_almoxarifado.application.PedidoEventConsumer;

import edu.br.infnet.petfriends_almoxarifado.domain.events.PedidoCriadoEvent;
import edu.br.infnet.petfriends_almoxarifado.domain.model.Estoque;
import edu.br.infnet.petfriends_almoxarifado.infrastructure.repository.EstoqueRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoEventConsumer {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @RabbitListener(queues = "pedidoQueue")
    public void processarPedido(PedidoCriadoEvent event) {
        Estoque estoque = estoqueRepository.findByProdutoId(event.getProdutoId());

        if (estoque != null) {
            estoque.getQuantidade().subtrair(1);
            estoqueRepository.save(estoque);
            System.out.println("Estoque atualizado para o pedido: " + event.getPedidoId());
        } else {
            System.out.println("Produto não encontrado no estoque.");
        }
    }
}
