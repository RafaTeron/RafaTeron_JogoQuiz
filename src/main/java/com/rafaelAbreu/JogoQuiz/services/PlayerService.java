package com.rafaelAbreu.JogoQuiz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelAbreu.JogoQuiz.entities.Answer;
import com.rafaelAbreu.JogoQuiz.entities.Player;
import com.rafaelAbreu.JogoQuiz.entities.Question;
import com.rafaelAbreu.JogoQuiz.exceptions.ErroScoreException;
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
				salvarQuestionRespondida(player);
			}
		}
	}

	public boolean conferirResposta(Long id, int opcao) throws ErroScoreException {
		Optional<Player> playerOptional = playerRepository.findById(id);

		if (playerOptional.isPresent()) {
			Player player = playerOptional.get();
			List<Answer> answerList = player.getQuestion().get(0).getAnswers();
			if (opcao > 0 && opcao <= answerList.size()) {
				Answer answerEscolhida = answerList.get(opcao - 1);
				if (answerEscolhida.getIsCorrect() == true) {
					somarScore(player);
					return true;
				}
			}
		}
		return false;
	}

	protected void salvarQuestionRespondida(Player player) {
		if (!player.getQuestion().isEmpty()) {
			String textoQuestion = player.getQuestion().get(0).getQuestionText();

			if (player.getQuestionRespondidas() == null) {
				player.setQuestionRespondidas(new ArrayList<>());
			}
			for (int i = 0; i < player.getQuestionRespondidas().size(); i++) {
				if (!player.getQuestionRespondidas().isEmpty())
					if (player.getQuestionRespondidas().get(i).equals(textoQuestion)) {
						do {
							alterar_E_Salvar_QuestionAleatoriaAoPlayer(player);
						} while (player.getQuestionRespondidas().get(i).equals(textoQuestion));
					}
			}
			player.getQuestionRespondidas().add(textoQuestion);
			playerRepository.save(player);
		}
	}

	public void somarScore(Player player) throws ErroScoreException {
		if (player.getPointScore() < 100) {
			player.setPointScore(player.getPointScore() + 10);
			playerRepository.save(player);
		} else {
			throw new ErroScoreException("Pontuação maxima!");
		}
	}

	protected void alterar_E_Salvar_QuestionAleatoriaAoPlayer(Player player) {
		List<Question> listQuestions = new ArrayList<>();
		Question questionAleatoria;

	    do {
	        questionAleatoria = questionRepository.encontrarQuestionAleatoria(); // Obtém uma pergunta aleatória
	    } while (jaFoiRespondida(player, questionAleatoria)); // Verifica se já foi respondida

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
	
	protected boolean jaFoiRespondida(Player player, Question question) {
	    List<String> perguntasRespondidas = player.getQuestionRespondidas();
	    if (perguntasRespondidas != null && !perguntasRespondidas.isEmpty()) {
	        return perguntasRespondidas.contains(question.getQuestionText());
	    }
	    return false;
	}

}