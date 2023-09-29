package com.quiz.quizbuilder.service;

import com.quiz.quizbuilder.dto.QuestionResponseDTO;
import com.quiz.quizbuilder.entity.Option;
import com.quiz.quizbuilder.entity.Question;

import java.util.List;
import java.util.Map;

public interface IQuestionService {
    Question save(Question question);

    List<Question> saveAll(List<Question> questions);

    Question update(Question question);

    Question getById(Long id);

    List<Question> getAllByIds(List<Long> ids);

    void delete(Long id);

    List<QuestionResponseDTO> getByQuiz(Long quizId);


    Map<Question, List<Option>> getQuestionOptionMap(Long quizId);
}
