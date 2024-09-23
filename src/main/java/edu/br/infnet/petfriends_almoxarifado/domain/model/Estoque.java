package edu.br.infnet.petfriends_almoxarifado.domain.model;


import edu.br.infnet.petfriends_almoxarifado.domain.vo.Quantidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long produtoId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String categoria;

    @Embedded
    private Quantidade quantidade;
}
