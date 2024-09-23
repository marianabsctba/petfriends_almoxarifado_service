package edu.br.infnet.petfriends_almoxarifado.application.service;

package edu.br.infnet.petfriends_almoxarifado.application;

import edu.br.infnet.petfriends_almoxarifado.domain.model.Estoque;
import edu.br.infnet.petfriends_almoxarifado.infrastructure.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    public Optional<Estoque> buscarPorProdutoId(Long produtoId) {
        return Optional.ofNullable(estoqueRepository.findByProdutoId(produtoId));
    }

    public Estoque atualizarEstoque(Long produtoId, int quantidade) {
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId);
        if (estoque != null) {
            estoque.getQuantidade().subtrair(quantidade);
            return estoqueRepository.save(estoque);
        } else {
            throw new RuntimeException("Produto não encontrado no estoque");
        }
    }
}
