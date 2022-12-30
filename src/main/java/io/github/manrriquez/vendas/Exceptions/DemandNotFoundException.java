package io.github.manrriquez.vendas.Exceptions;

public class DemandNotFoundException extends RuntimeException{

    public DemandNotFoundException() {
        super("Pedido não encontrado.");
    }
}
