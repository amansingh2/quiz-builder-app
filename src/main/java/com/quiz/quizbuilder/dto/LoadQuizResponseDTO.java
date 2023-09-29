package com.quiz.quizbuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoadQuizResponseDTO {
    private String title;
    private String description;
    private Integer numberOfQuestions;
    private Integer maxMarks;
    private Long timeInSeconds;
    private List<QuestionResponseDTO> questions;
}
