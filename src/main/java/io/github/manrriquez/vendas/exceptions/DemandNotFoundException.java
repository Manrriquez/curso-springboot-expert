package io.github.manrriquez.vendas.exceptions;

public class DemandNotFoundException extends RuntimeException {

    public DemandNotFoundException() {
        super("Pedido não encontrado.");
    }
}
