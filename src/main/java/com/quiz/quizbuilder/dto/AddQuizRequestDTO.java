package com.quiz.quizbuilder.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddQuizRequestDTO {
    private UpdateQuizDto quiz;
    private List<AddQuestionRequestDTO> questions;
}
