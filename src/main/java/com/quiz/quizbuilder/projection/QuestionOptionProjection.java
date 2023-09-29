package com.quiz.quizbuilder.projection;

import com.quiz.quizbuilder.entity.Option;
import com.quiz.quizbuilder.entity.Question;

public interface QuestionOptionProjection {
    Question getQuestion();
    Option getOption();
}
