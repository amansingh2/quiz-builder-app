package com.quiz.quizbuilder.projection;

import com.quiz.quizbuilder.entity.Option;
import com.quiz.quizbuilder.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionDto {

    private Question question;
    private Option options;
}
