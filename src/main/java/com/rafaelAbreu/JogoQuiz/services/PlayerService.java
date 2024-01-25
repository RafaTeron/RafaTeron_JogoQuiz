package com.rafaelAbreu.JogoQuiz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelAbreu.JogoQuiz.entities.Player;
import com.rafaelAbreu.JogoQuiz.entities.Question;
import com.rafaelAbreu.JogoQuiz.repositories.PlayerRepository;
import com.rafaelAbreu.JogoQuiz.repositories.QuestionRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private QuestionRepository questionRepository;

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

    public Player update(Long id, Player player) {
        Player entity = playerRepository.getReferenceById(id);
        updateDaTa(entity, player);
        return playerRepository.save(entity);
    }

    protected void updateDaTa(Player entity, Player player) {
        entity.setName(player.getName());
    }

    public void gerarQuestionParaPlayer(Long id) {
        Optional<Player> playerOptional = playerRepository.findById(id);

        if (playerOptional.isPresent() ) {
            Player player = playerOptional.get();

            List<Question> listQuestions = new ArrayList<>();
            Question questionAleatoria = questionRepository.encontrarQuestionAleatoria();
            listQuestions.add(questionAleatoria);
            player.setQuestion(listQuestions);
            playerRepository.save(player);
        }
    }

    public void somarScore() {

    }

    protected boolean playerQuestionVazio(Player player){
        if (player.getQuestion() == null || player.getQuestion().isEmpty()) {
            return true;
        }
        return false;
    }

}
