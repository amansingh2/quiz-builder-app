package com.quiz.quizbuilder.service.impl;

import com.quiz.quizbuilder.entity.QuestionAttempt;
import com.quiz.quizbuilder.repository.QuestionAttemptRepository;
import com.quiz.quizbuilder.service.IQuestionAttemptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionAttemptService implements IQuestionAttemptService {

    private final QuestionAttemptRepository questionAttemptRepository;

    @Override
    public QuestionAttempt create(QuestionAttempt questionAttempt) {
        return questionAttemptRepository.save(questionAttempt);
    }

    @Override
    public QuestionAttempt getById(Long id) {
        return questionAttemptRepository.findById(id).orElse(new QuestionAttempt());
    }

    @Override
    public QuestionAttempt findByUserQuestionQuiz(Long userId, Long quesId, Long quizId) {
        return questionAttemptRepository.findByUserIdAndQuestionIdAndQuizId(userId, quesId, quizId);
    }

    @Override
    public List<QuestionAttempt> saveAll(List<QuestionAttempt> questionAttempts) {
        return questionAttemptRepository.saveAll(questionAttempts);
    }

    @Override
    public List<QuestionAttempt> findByUserAndQuiz(Long userId, Long quizId) {
        return questionAttemptRepository.findByUser_IdAndQuiz_Id(userId,quizId);
    }

}
