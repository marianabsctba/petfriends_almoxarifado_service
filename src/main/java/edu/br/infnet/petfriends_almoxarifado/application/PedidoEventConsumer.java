package edu.br.infnet.petfriends_almoxarifado.application;

import edu.br.infnet.petfriends_almoxarifado.domain.events.PedidoCriadoEvent;
import edu.br.infnet.petfriends_almoxarifado.domain.model.Estoque;
import edu.br.infnet.petfriends_almoxarifado.infrastructure.repository.EstoqueRepository;
import edu.br.infnet.petfriends_almoxarifado.infrastructure.clients.ProdutoClient;
import edu.br.infnet.petfriends_almoxarifado.infrastructure.dto.ProdutoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoEventConsumer {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoClient produtoClient;

    @RabbitListener(queues = "${broker.queue.pedido.name}")
    public void processarPedido(PedidoCriadoEvent event) {
        ProdutoDTO produto = produtoClient.buscarProdutoPorId(event.getProdutoId());

        if (produto != null) {
            Optional<Estoque> optionalEstoque = estoqueRepository.findByProdutoId(event.getProdutoId());

            if (optionalEstoque.isPresent()) {
                Estoque estoque = optionalEstoque.get();
                estoque.subtrairQuantidade(event.getQuantidade());

                estoqueRepository.save(estoque);

                System.out.println("Estoque atualizado para o produto: " + event.getProdutoId() + ", Pedido: " + event.getPedidoId());
            } else {
                System.err.println("Produto não encontrado no estoque para o ID: " + event.getProdutoId());
            }
        } else {
            System.err.println("Produto não encontrado no serviço de produtos para o ID: " + event.getProdutoId());
        }
    }
}
