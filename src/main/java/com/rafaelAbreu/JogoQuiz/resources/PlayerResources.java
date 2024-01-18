package com.rafaelAbreu.JogoQuiz.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelAbreu.JogoQuiz.entities.Player;
import com.rafaelAbreu.JogoQuiz.services.PlayerService;

@RestController
@RequestMapping(value = "/players")
public class PlayerResources {
    
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> findAll(){
        List<Player> list = playerService.findAll();
        return ResponseEntity.ok().body(list);
    }

}
