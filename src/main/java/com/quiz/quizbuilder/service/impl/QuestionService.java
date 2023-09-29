package com.quiz.quizbuilder.service.impl;

import com.quiz.quizbuilder.dto.OptionDto;
import com.quiz.quizbuilder.dto.QuestionDto;
import com.quiz.quizbuilder.dto.QuestionResponseDTO;
import com.quiz.quizbuilder.entity.Option;
import com.quiz.quizbuilder.entity.Question;
import com.quiz.quizbuilder.projection.QuestionOptionProjection;
import com.quiz.quizbuilder.repository.QuestionRepository;
import com.quiz.quizbuilder.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> saveAll(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }

    @Override
    public Question update(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getById(Long id) {
        return questionRepository.findById(id).orElse(new Question());
    }

    @Override
    public List<Question> getAllByIds(List<Long> ids) {
        return questionRepository.findAllById(ids);
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<QuestionResponseDTO> getByQuiz(Long quizId) {

        List<QuestionOptionProjection> sqlResult = questionRepository.getQuestionsForQuiz(quizId);
        Map<Question, List<Option>> questionListMap = new HashMap<>();
        for (QuestionOptionProjection qop : sqlResult) {
            List<Option> temp = questionListMap.getOrDefault(qop.getQuestion(), new ArrayList<>());
            temp.add(qop.getOption());
            questionListMap.put(qop.getQuestion(), temp);
        }
        List<QuestionResponseDTO> res = new ArrayList<>();
        for (Map.Entry<Question, List<Option>> entry :
                questionListMap.entrySet()) {
            res.add(QuestionResponseDTO.builder().question(QuestionDto.builder().body(entry.getKey().getBody()).build())
                            .options(entry.getValue().stream().map(o -> OptionDto.builder().id(o.getId())
                                    .body(o.getBody()).build()).collect(Collectors.toList()))
                            .isMultiChoice(entry.getKey().isMultiChoice())
                    .build());
        }
        return res;
    }

    @Override
    public Map<Question, List<Option>> getQuestionOptionMap(Long quizId) {
        List<QuestionOptionProjection> projections = questionRepository.getQuestionsAndAnswersByIds(quizId);
        Map<Question, List<Option>> res = new HashMap<>();
        for (QuestionOptionProjection qop : projections) {
            List<Option> temp = res.getOrDefault(qop.getQuestion(), new ArrayList<>());
            temp.add(qop.getOption());
            res.put(qop.getQuestion(), temp);
        }
        return res;
    }


}
