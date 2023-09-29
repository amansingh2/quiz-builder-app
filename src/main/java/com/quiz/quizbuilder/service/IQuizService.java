package com.quiz.quizbuilder.service;

import com.quiz.quizbuilder.entity.Quiz;
import com.quiz.quizbuilder.entity.User;
import com.quiz.quizbuilder.dto.AddQuizRequestDTO;
import com.quiz.quizbuilder.dto.LoadQuizResponseDTO;
import com.quiz.quizbuilder.dto.QuizResultDTO;
import com.quiz.quizbuilder.dto.QuizSubmitDTO;
import com.quiz.quizbuilder.dto.UpdateQuizDto;

import java.util.List;
import java.util.Map;

public interface IQuizService {
    Quiz create(AddQuizRequestDTO quizRequestDTO, User user);

    Quiz update(UpdateQuizDto request, Long quizId, Long userId);

    Quiz getById(Long id);

    LoadQuizResponseDTO loadQuiz(Long quizId, Long userId);

    void delete(Long id, Long userId);

    QuizResultDTO submitQuiz(Long quizId, List<QuizSubmitDTO> submission, User user);

    Map<String,String> publishQuiz(Long quizId, Long userId);
}
