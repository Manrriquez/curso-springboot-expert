package io.github.manrriquez.vendas.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebConfig {

    @Bean(name = "applicationName")
    public String applicationName() {
        return "Sistema de vendas";
    }
}
