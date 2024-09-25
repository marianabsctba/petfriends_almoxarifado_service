package edu.br.infnet.petfriends_almoxarifado.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quantidade {

    private int valor;

    public void adicionar(int quantidade) {
        this.valor += quantidade;
    }

    public void subtrair(int quantidade) {
        if (this.valor >= quantidade) {
            this.valor -= quantidade;
        } else {
            throw new IllegalArgumentException("Opsss.. não há estoque suficiente. Tente novamente mais tarde");
        }
    }
}
