package com.learningTech.Quizzapp.DAO;

import com.learningTech.Quizzapp.model.Question;
import com.learningTech.Quizzapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizDAO extends JpaRepository<Quiz, Integer> {

}
