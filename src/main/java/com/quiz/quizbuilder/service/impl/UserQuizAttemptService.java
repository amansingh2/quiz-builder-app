package com.quiz.quizbuilder.service.impl;

import com.quiz.quizbuilder.dto.UserQuizAttemptDTO;
import com.quiz.quizbuilder.entity.UserQuizAttempt;
import com.quiz.quizbuilder.service.IUserQuizAttemptService;
import com.quiz.quizbuilder.repository.UserQuizAttemptRepository;
import com.quiz.quizbuilder.service.IResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserQuizAttemptService implements IUserQuizAttemptService {

    private final UserQuizAttemptRepository userQuizAttemptRepository;
    private final IResultService resultService;

    @Override
    public UserQuizAttempt save(UserQuizAttempt userQuizAttempt) {
        return userQuizAttemptRepository.save(userQuizAttempt);
    }

    @Override
    public boolean hasUserAttemptedQuiz(Long userId, Long quizId) {
        return userQuizAttemptRepository.existsByUserIdAndQuizId(userId, quizId);
    }

    @Override
    public List<UserQuizAttemptDTO> getAttemptedUsers(Long quizId) {
        return resultService.findByQuiz(quizId).stream()
                .map(result -> UserQuizAttemptDTO.builder()
                        .quizId(result.getUserQuizAttempt().getQuiz().getId())
                        .username(result.getUserQuizAttempt().getUser().getUsername())
                        .userId(result.getUserQuizAttempt().getUser().getId())
                        .score(result.getScore())
                        .totalQuestions(result.getTotalQuestions())
                        .build())
                .collect(Collectors.toList());
    }
}
