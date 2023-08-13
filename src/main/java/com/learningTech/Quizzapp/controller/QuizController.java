package com.learningTech.Quizzapp.controller;

import com.learningTech.Quizzapp.model.Question;
import com.learningTech.Quizzapp.model.QuestionWrapper;
import com.learningTech.Quizzapp.model.Response;
import com.learningTech.Quizzapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizservice;
  @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int NoOfQues, @RequestParam String title){
        return quizservice.createQuiz(category, NoOfQues, title);

    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id ){
      return quizservice.getQuizQuestions(id);

    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){
      return quizservice.submitQuiz(id,response);

    }
}
