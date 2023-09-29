package com.quiz.quizbuilder.repository;

import com.quiz.quizbuilder.entity.QuestionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionAttemptRepository extends JpaRepository<QuestionAttempt, Long> {
    List<QuestionAttempt> findByUser_IdAndQuiz_Id(long userId, long quizId);
    QuestionAttempt findByUserIdAndQuestionIdAndQuizId(long userId, long quesId, long quizId);
}