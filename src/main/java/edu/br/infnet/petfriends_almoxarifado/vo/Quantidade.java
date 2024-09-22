package edu.br.infnet.petfriends_almoxarifado.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Quantidade {

    private int valor;

    public Quantidade(int valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
        this.valor = valor;
    }

    public Quantidade() {

    }

    public int getValor() {
        return valor;
    }

    public void adicionar(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade a adicionar não pode ser negativa.");
        }
        this.valor += quantidade;
    }

    public void subtrair(int quantidade) {
        if (quantidade < 0 || this.valor < quantidade) {
            throw new IllegalArgumentException("Quantidade inválida.");
        }
        this.valor -= quantidade;
    }
}