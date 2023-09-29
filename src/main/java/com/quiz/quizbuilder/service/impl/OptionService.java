package com.quiz.quizbuilder.service.impl;

import com.quiz.quizbuilder.entity.Option;
import com.quiz.quizbuilder.repository.OptionRepository;
import com.quiz.quizbuilder.service.IOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OptionService implements IOptionService {

    private final OptionRepository optionRepository;


    @Override
    public List<Option> saveAll(List<Option> options) {
        return optionRepository.saveAll(options);
    }

    @Override
    public Option update(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public Option getById(Long id) {
        return optionRepository.findById(id).orElse(new Option());
    }

    @Override
    public void delete(Long id) {
        optionRepository.deleteById(id);
    }

    @Override
    public Map<Long, Integer> getOptionCountForQuestions(List<Long> qids) {
        return optionRepository.getOptionCountForQuestions(qids).stream().collect(Collectors.toMap(k->k.getQuestionId(),v -> v.getOptionCount()));
    }
}
