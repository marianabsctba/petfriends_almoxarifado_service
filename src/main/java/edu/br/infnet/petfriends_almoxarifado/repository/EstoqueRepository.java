package edu.br.infnet.petfriends_almoxarifado.repository;

import edu.br.infnet.petfriends_almoxarifado.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Estoque findByProdutoId(Long produtoId);
}
