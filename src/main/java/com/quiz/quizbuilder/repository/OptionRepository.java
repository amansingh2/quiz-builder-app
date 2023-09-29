package com.quiz.quizbuilder.repository;

import com.quiz.quizbuilder.entity.Option;
import com.quiz.quizbuilder.projection.QuestionOptionCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    @Query(value = "select o.question.id as questionId, count(o) as optionCount from Option o " +
            "where o.question.id in :qids " +
            "group by o.question.id")
    List<QuestionOptionCountProjection> getOptionCountForQuestions(List<Long> qids);
}