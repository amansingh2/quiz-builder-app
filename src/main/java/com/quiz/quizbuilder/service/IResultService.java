package com.quiz.quizbuilder.service;

import com.quiz.quizbuilder.entity.Result;

import java.util.List;

public interface IResultService {

    Result save(Result result);

    List<Result> findByQuiz(Long quizId);

}
