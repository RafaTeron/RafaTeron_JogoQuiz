package com.rafaelAbreu.JogoQuiz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelAbreu.JogoQuiz.entities.Player;
import com.rafaelAbreu.JogoQuiz.repositories.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findById(Long id) throws RuntimeException {
        Optional<Player> obj = playerRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        } else {
            throw new RuntimeException("Player não encontrado");
        }
    }

    public Player insert(Player player) {
		return playerRepository.save(player);
	}

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
