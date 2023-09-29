package com.quiz.quizbuilder.repository;

import com.quiz.quizbuilder.entity.UserQuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuizAttemptRepository extends JpaRepository<UserQuizAttempt, Long> {
     List<UserQuizAttempt> findByQuiz_Id(long id);
     boolean existsByUserIdAndQuizId(Long userId, Long quizId);
}