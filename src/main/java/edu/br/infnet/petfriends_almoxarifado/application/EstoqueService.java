package edu.br.infnet.petfriends_almoxarifado.application;

import edu.br.infnet.petfriends_almoxarifado.domain.model.Estoque;
import edu.br.infnet.petfriends_almoxarifado.infrastructure.clients.ProdutoClient;
import edu.br.infnet.petfriends_almoxarifado.infrastructure.repository.EstoqueRepository;
import edu.br.infnet.petfriends_almoxarifado.infrastructure.dto.ProdutoDTO;
import edu.br.infnet.petfriends_almoxarifado.domain.vo.Quantidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoClient produtoClient;

    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    public Optional<Estoque> buscarPorProdutoId(Long produtoId) {
        ProdutoDTO produto = produtoClient.buscarProdutoPorId(produtoId);
        if (produto != null) {
            return estoqueRepository.findByProdutoId(produtoId);
        } else {
            throw new RuntimeException("Produto não encontrado no serviço de produtos");
        }
    }

    public Estoque atualizarEstoque(Long produtoId, int quantidade) {
        ProdutoDTO produto = produtoClient.buscarProdutoPorId(produtoId);
        if (produto != null) {
            Estoque estoque = estoqueRepository.findByProdutoId(produtoId)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado no estoque"));

            estoque.subtrairQuantidade(quantidade);
            return estoqueRepository.save(estoque);
        } else {
            throw new RuntimeException("Produto não encontrado no serviço de produtos");
        }
    }

    public Estoque adicionarEstoque(Long produtoId, int quantidade) {
        ProdutoDTO produto = produtoClient.buscarProdutoPorId(produtoId);
        if (produto != null) {
            Optional<Estoque> optionalEstoque = estoqueRepository.findByProdutoId(produtoId);
            Estoque estoque;

            if (optionalEstoque.isPresent()) {
                estoque = optionalEstoque.get();
                estoque.adicionarQuantidade(quantidade);
            } else {
                estoque = Estoque.builder()
                        .produtoId(produtoId)
                        .nome(produto.getNome())
                        .quantidade(new Quantidade(quantidade))
                        .build();
            }

            return estoqueRepository.save(estoque);
        } else {
            throw new RuntimeException("Produto não encontrado no serviço de produtos");
        }
    }
}
