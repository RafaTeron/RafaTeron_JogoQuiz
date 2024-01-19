package com.rafaelAbreu.JogoQuiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelAbreu.JogoQuiz.entities.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>{

}
