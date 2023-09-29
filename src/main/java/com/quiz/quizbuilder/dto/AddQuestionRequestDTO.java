package com.quiz.quizbuilder.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddQuestionRequestDTO {
    private String question;
    private Boolean isMultiChoice;
    private List<OptionBodyCorrectDto> options;
}
