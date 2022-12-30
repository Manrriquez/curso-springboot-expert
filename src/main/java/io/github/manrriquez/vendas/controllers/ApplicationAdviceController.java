package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.Errors.ApiErrors;
import io.github.manrriquez.vendas.Exceptions.DemandNotFoundException;
import io.github.manrriquez.vendas.Exceptions.RuleBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ApplicationAdviceController {


    @ExceptionHandler(RuleBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRuleBusinnesException(RuleBusinessException rule) {

        String errorMessage = rule.getMessage();

        return new ApiErrors(errorMessage);
    }

    @ExceptionHandler(DemandNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleDemandNotFoundException(DemandNotFoundException ex) {
        return new ApiErrors((ex.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error ->
                        error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErrors(errors);
    }
}
