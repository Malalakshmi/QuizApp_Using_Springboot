package com.learningTech.Quizzapp.DAO;

import com.learningTech.Quizzapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {


    public List<Question> findBycategory(String category);

    @Query(value = "SELECT * FROM question q where q.category = :category ORDER BY Random() Limit :noOfQues", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int noOfQues);
}
