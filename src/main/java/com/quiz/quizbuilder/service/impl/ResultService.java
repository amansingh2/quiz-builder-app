package com.quiz.quizbuilder.service.impl;

import com.quiz.quizbuilder.entity.Result;
import com.quiz.quizbuilder.repository.ResultRepository;
import com.quiz.quizbuilder.service.IResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResultService implements IResultService {

    private final ResultRepository resultRepository;

    public Result save(Result result){
        return resultRepository.save(result);
    }

    @Override
    public List<Result> findByQuiz(Long quizId) {
        return resultRepository.findByUserQuizAttempt_Quiz_Id(quizId);
    }


}