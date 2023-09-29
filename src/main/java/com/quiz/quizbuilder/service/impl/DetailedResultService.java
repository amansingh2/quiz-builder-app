package com.quiz.quizbuilder.service.impl;

import com.quiz.quizbuilder.dto.DetailedQuestionResponseDTO;
import com.quiz.quizbuilder.dto.DetailedResultDTO;
import com.quiz.quizbuilder.dto.OptionDto;
import com.quiz.quizbuilder.dto.QuestionDto;
import com.quiz.quizbuilder.entity.Option;
import com.quiz.quizbuilder.entity.Question;
import com.quiz.quizbuilder.entity.QuestionAttempt;
import com.quiz.quizbuilder.service.IDetailedResultService;
import com.quiz.quizbuilder.service.IOptionService;
import com.quiz.quizbuilder.service.IQuestionAttemptService;
import com.quiz.quizbuilder.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DetailedResultService implements IDetailedResultService {
    private final IQuestionAttemptService questionAttemptService;
    private final IQuestionService questionService;
    private final IOptionService optionService;

    public DetailedResultDTO generate(Long quizId, Long userId) {
        log.info("Generating detailed result of quiz ID : {} for user ID : {}", quizId, userId);
        List<DetailedQuestionResponseDTO> questionAttempt = new ArrayList<>();
        Map<Question, List<Option>> questionOptionMap = questionService.getQuestionOptionMap(quizId);
        for (QuestionAttempt attempt : questionAttemptService.findByUserAndQuiz(userId, quizId)) {
            List<OptionDto> responses = attempt.getResponse().stream().map(id -> {
                Option option = optionService.getById(id);
                return OptionDto.builder().id(option.getId()).body(option.getBody()).build();
            }).collect(Collectors.toList());
            List<OptionDto> options = new ArrayList<>();
            for (Map.Entry<Question, List<Option>> entry : questionOptionMap.entrySet()) {
                if (entry.getKey().equals(attempt.getQuestion())) {
                    options = entry.getValue().stream().map(o -> {
                        return OptionDto.builder().id(o.getId()).body(o.getBody()).build();
                    }).collect(Collectors.toList());
                    break;
                }
            }
            questionAttempt.add(DetailedQuestionResponseDTO.builder()
                    .question(QuestionDto.builder().id(attempt.getQuestion().getId()).body(attempt.getQuestion().getBody()).build())
                    .options(options).responses(responses).isMultiChoice(attempt.getQuestion().isMultiChoice()).build());
        }
        log.info("Detailed result generated");
        return DetailedResultDTO.builder().detailedQuestionResponse(questionAttempt).build();
    }
}
