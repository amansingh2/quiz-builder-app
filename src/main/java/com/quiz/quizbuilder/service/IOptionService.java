package com.quiz.quizbuilder.service;

import com.quiz.quizbuilder.entity.Option;

import java.util.List;
import java.util.Map;

public interface IOptionService {

    List<Option> saveAll(List<Option> options);

    Option update(Option option);

    Option getById(Long id);

    void delete(Long id);

    Map<Long, Integer> getOptionCountForQuestions(List<Long> qids);


}
