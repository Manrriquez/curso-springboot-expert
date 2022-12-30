package io.github.manrriquez.vendas.Errors;


import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String errorMessage) {
        this.errors = Arrays.asList(errorMessage);
    }
}
