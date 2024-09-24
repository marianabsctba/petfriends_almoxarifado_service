package edu.br.infnet.petfriends_almoxarifado.domain.model;

import edu.br.infnet.petfriends_almoxarifado.domain.vo.Quantidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long produtoId;

    @Column(nullable = false)
    private String nome;

    @Embedded
    private Quantidade quantidade;

    public void subtrairQuantidade(int quantidade) {
        this.quantidade.subtrair(quantidade);
    }

    public void adicionarQuantidade(int quantidade) {
        this.quantidade.adicionar(quantidade);
    }
}
