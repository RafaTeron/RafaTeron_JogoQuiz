package com.rafaelAbreu.JogoQuiz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelAbreu.JogoQuiz.entities.Answer;
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

        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            
            if (verificarPlayerQuestionVazio(player)) {
                alterar_E_Salvar_QuestionAleatoriaAoPlayer(player);
                playerRepository.save(player);
            }
        }
    }

    public boolean conferirResposta(Long id, int opcao){
        Optional<Player> playerOptional = playerRepository.findById(id);

        if (playerOptional.isPresent()) {
        	Player player = playerOptional.get();
            List<Answer> answerList = player.getQuestion().get(0).getAnswers();
            if(opcao > 0 && opcao <= answerList.size()) {
            	Answer answerEscolhida = answerList.get(opcao - 1);
            	if(answerEscolhida.getIsCorrect() == true) {
            		somarScore(player);
            		return true;
            	}
            }
        }    
        return false;
    }

    public void somarScore(Player player) {    
        player.setPointScore(player.getPointScore() + 10);
        playerRepository.save(player);
    }

    protected void alterar_E_Salvar_QuestionAleatoriaAoPlayer(Player player) {
        List<Question> listQuestions = new ArrayList<>();
        Question questionAleatoria = questionRepository.encontrarQuestionAleatoria();
        List<Player> players = new ArrayList<>();
        players.add(player);
        questionAleatoria.setPlayers(players);
        listQuestions.add(questionAleatoria);
        player.setQuestion(listQuestions);
        questionRepository.save(questionAleatoria);
    }

    protected boolean verificarPlayerQuestionVazio(Player player) {
        if (player.getQuestion() == null || player.getQuestion().isEmpty()) {
            return true;
        } else {
            player.getQuestion().remove(0);
            return true;
        }
    }

}