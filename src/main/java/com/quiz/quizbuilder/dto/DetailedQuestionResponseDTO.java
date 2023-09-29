package com.quiz.quizbuilder.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DetailedQuestionResponseDTO {
    private QuestionDto question;
    private Boolean isMultiChoice;
    private List<OptionDto> options;
    private List<OptionDto> responses;
}
