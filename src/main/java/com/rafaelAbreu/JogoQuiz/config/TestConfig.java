package com.rafaelAbreu.JogoQuiz.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rafaelAbreu.JogoQuiz.entities.Answer;
import com.rafaelAbreu.JogoQuiz.entities.Player;
import com.rafaelAbreu.JogoQuiz.entities.Question;
import com.rafaelAbreu.JogoQuiz.entities.enums.Category;
import com.rafaelAbreu.JogoQuiz.repositories.AnswerRepository;
import com.rafaelAbreu.JogoQuiz.repositories.PlayerRepository;
import com.rafaelAbreu.JogoQuiz.repositories.QuestionRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {

        Player p1 = new Player(null, "Rafael", 0);
        Player p2 = new Player(null, "Thayna", 0);

        playerRepository.saveAll(Arrays.asList(p1, p2));

        Question q1 = new Question(null, "Qual é o elemento mais abundante na crosta terrestre?", null,
                Category.CIENCIA);
        Question q2 = new Question(null,"Qual jogador de futebol brasileiro é frequentemente chamado de 'O Fenômeno'?",null,Category.ESPORTE);
        Question q3 = new Question(null,"Quem interpretou o personagem Jack Dawson no filme 'Titanic' de 1997?",null,Category.ENTRETENIMENTO);
        Question q4 = new Question(null,"Em que ano a Segunda Guerra Mundial começou?",null,Category.HISTORIA);
        Question q5 = new Question(null,"Qual é a capital da Austrália?",null,Category.GEOGRAFIA);
        Question q6 = new Question(null,"Qual país sediou os Jogos Olímpicos de Verão de 2020 (realizados em 2021)?",null,Category.ATUALIDADE);
        Question q7 = new Question(null,"Quem é o co-fundador da Microsoft, junto com Bill Gates?",null,Category.TECNOLOGIA);
        Question q8 = new Question(null,"Qual é o maior oceano do mundo em termos de área?",null,Category.MEIO_AMBIENTE);
        Question q9 = new Question(null,"Na mitologia grega, quem é o deus do vinho e das festas?",null,Category.MITOS_E_LENDAS);
        Question q10 = new Question(null,"Qual país é conhecido por ser o berço do sushi?",null,Category.CULINARIA);

        questionRepository.saveAll(Arrays.asList(q1,q2,q3,q4,q5,q6,q7,q8,q9,q10));
    }

}
