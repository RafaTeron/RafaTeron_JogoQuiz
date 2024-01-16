package com.rafaelAbreu.JogoQuiz.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig  implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
         // Lógica para inicialização ou configuração quando a aplicação é iniciada
         System.out.println("TestConfig - Inicializando...");
         // Adicione sua lógica aqui
    }
    
}
