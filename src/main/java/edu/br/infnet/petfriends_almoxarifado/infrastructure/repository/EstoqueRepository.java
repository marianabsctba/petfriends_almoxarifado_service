package edu.br.infnet.petfriends_almoxarifado.infrastructure.repository;

import edu.br.infnet.petfriends_almoxarifado.domain.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByProdutoId(Long produtoId);
}
