package com.rafaelAbreu.JogoQuiz.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rafaelAbreu.JogoQuiz.entities.Player;
import com.rafaelAbreu.JogoQuiz.repositories.PlayerRepository;

@Configuration
@Profile("test")
public class TestConfig  implements CommandLineRunner {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
         
        Player p1 = new Player(null,"Rafael");
        Player p2 = new Player(null,"Thayna");

        playerRepository.saveAll(Arrays.asList(p1,p2));
    }
    
}
