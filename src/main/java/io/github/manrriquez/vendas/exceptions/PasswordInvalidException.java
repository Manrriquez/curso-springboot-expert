package io.github.manrriquez.vendas.exceptions;

public class PasswordInvalidException extends RuntimeException{

    public PasswordInvalidException() {
        super("Senha inválida.");
    }
}
