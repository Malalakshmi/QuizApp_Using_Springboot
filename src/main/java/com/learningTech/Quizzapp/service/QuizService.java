package com.learningTech.Quizzapp.service;

import com.learningTech.Quizzapp.DAO.QuestionDAO;
import com.learningTech.Quizzapp.DAO.QuizDAO;
import com.learningTech.Quizzapp.model.Question;
import com.learningTech.Quizzapp.model.QuestionWrapper;
import com.learningTech.Quizzapp.model.Quiz;
import com.learningTech.Quizzapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<String> createQuiz(String category, int noOfQues, String title) {
        List<Question> questions = questionDAO.findRandomQuestionByCategory(category,noOfQues);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
       List<Question> questionsFromDB = quiz.get().getQuestions();
       List<QuestionWrapper> questionsForUser = new ArrayList<>();
       for(Question question : questionsFromDB){
           QuestionWrapper qw = new QuestionWrapper(question.getId(),question.getQuestionTitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
           questionsForUser.add(qw);
       }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> response) {
        Quiz quiz = quizDAO.findById(id).get();
        List<Question> questions =  quiz.getQuestions();
        int right = 0;
        int i =0;
        for(Response res : response){
            if(res.getResponse().equals(questions.get(i).getRightAnswer())) {
                right++;
            }
                i++;


        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
