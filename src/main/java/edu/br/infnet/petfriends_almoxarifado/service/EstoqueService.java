package edu.br.infnet.petfriends_almoxarifado.service;

import edu.br.infnet.petfriends_almoxarifado.dto.EstoqueDto;
import edu.br.infnet.petfriends_almoxarifado.dto.PedidoEventoDto;
import edu.br.infnet.petfriends_almoxarifado.model.Estoque;
import edu.br.infnet.petfriends_almoxarifado.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public void processarEstoque(PedidoEventoDto estoque) {
        Estoque estoqueProduto = estoqueRepository.findByProdutoId(estoque.getProdutoId());

        if (estoqueProduto == null) {
            System.out.println("Produto não encontrado no estoque: " + estoque.getProdutoId());
            throw new RuntimeException("Produto não encontrado");
        }

        estoqueProduto.getQuantidade().subtrair(estoque.getQuantidade());
        estoqueRepository.save(estoqueProduto);
        System.out.println("Estoque atualizado para o produto: " + estoqueProduto.getProdutoId());
    }

    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    public Optional<Estoque> buscarPorProdutoId(Long id) {
        return Optional.ofNullable(estoqueRepository.findByProdutoId(id));
    }

    public Estoque criarEstoque(EstoqueDto estoque) {
       Estoque newEstoque = new Estoque();
       newEstoque.setProdutoId(estoque.getProdutoId());
       newEstoque.getQuantidade().adicionar(estoque.getQuantidade());
       newEstoque.setNome(estoque.getNome());
       newEstoque.setCategoria(estoque.getCategoria());

        return estoqueRepository.save(newEstoque);
    }

    public void excluirProduto(Long id) {
        Estoque estoqueProduto = estoqueRepository.findByProdutoId(id);

        if (estoqueProduto != null) {
            estoqueRepository.delete(estoqueProduto);
        }

        throw new RuntimeException("Produto não encontrado");
    }



}
