package com.quiz.quizbuilder.repository;

import com.quiz.quizbuilder.entity.Question;
import com.quiz.quizbuilder.projection.QuestionOptionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> getByQuizId(long id);

    @Query(value = "select q as question, o as option " +
            "from Question q " +
            "join Option o on o.question.id = q.id " +
            "where q.quiz.id = :quizId")
    List<QuestionOptionProjection> getQuestionsForQuiz(Long quizId);

    @Query("select q from Question q where q.id in :ids")
    List<Question> findAllById(List<Long> ids);


//    @Query(value = "select q as question, o as option from Question q join Option o on o.question.id = q.id " +
//            "where q.id in :ids and o.correct = true")

    @Query(value = "select q as question, o as option from Question q join Option o on o.question.id = q.id " +
            "where q.quiz.id = :qid and o.correct = true")
    List<QuestionOptionProjection> getQuestionsAndAnswersByIds(Long qid);

}