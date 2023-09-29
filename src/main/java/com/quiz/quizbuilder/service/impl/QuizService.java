package com.quiz.quizbuilder.service.impl;

import com.quiz.quizbuilder.repository.QuizRepository;
import com.quiz.quizbuilder.dto.AddQuizRequestDTO;
import com.quiz.quizbuilder.dto.LoadQuizResponseDTO;
import com.quiz.quizbuilder.dto.QuestionResponseDTO;
import com.quiz.quizbuilder.dto.QuizResultDTO;
import com.quiz.quizbuilder.dto.QuizSubmitDTO;
import com.quiz.quizbuilder.dto.UpdateQuizDto;
import com.quiz.quizbuilder.entity.Option;
import com.quiz.quizbuilder.entity.Question;
import com.quiz.quizbuilder.entity.QuestionAttempt;
import com.quiz.quizbuilder.entity.Quiz;
import com.quiz.quizbuilder.entity.Result;
import com.quiz.quizbuilder.entity.User;
import com.quiz.quizbuilder.entity.UserQuizAttempt;
import com.quiz.quizbuilder.service.IOptionService;
import com.quiz.quizbuilder.service.IQuestionAttemptService;
import com.quiz.quizbuilder.service.IQuestionService;
import com.quiz.quizbuilder.service.IQuizService;
import com.quiz.quizbuilder.service.IResultService;
import com.quiz.quizbuilder.service.IUserQuizAttemptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizService implements IQuizService {

    private final QuizRepository quizRepository;
    private final IQuestionService questionService;
    private final IOptionService optionService;
    private final IUserQuizAttemptService userQuizAttemptService;
    private final ExecutorService executorService;
    private final IQuestionAttemptService questionAttemptService;
    private final IResultService resultService;

    private static final int MARKS_PER_QUESTION = 1;

    @Override
    public Quiz create(AddQuizRequestDTO quizRequestDTO, User user) {
        log.info("Creating quiz with owner ID : {}...", user.getId());
        Quiz quiz = Quiz.builder().title(quizRequestDTO.getQuiz().getTitle()).description(quizRequestDTO.getQuiz().getDescription()).numberOfQuestions(quizRequestDTO.getQuiz().getNumberOfQuestions()).timeInSeconds(quizRequestDTO.getQuiz().getTimeInSeconds()).maxMarks(quizRequestDTO.getQuiz().getNumberOfQuestions() * MARKS_PER_QUESTION).owner(user).build();

        Quiz savedQuiz = quizRepository.save(quiz);

        executorService.execute(() -> {
            List<Question> questions = quizRequestDTO.getQuestions().parallelStream().map(q -> Question.builder().body(q.getQuestion()).isMultiChoice(q.getIsMultiChoice() != null && q.getIsMultiChoice()).quiz(savedQuiz).build()).collect(Collectors.toList());
            var questionsSaved = questionService.saveAll(questions);
            List<Option> options = new ArrayList<>();
            for (Question q : questionsSaved) {
                options.addAll(quizRequestDTO.getQuestions().parallelStream().filter(dto -> dto.getQuestion().equalsIgnoreCase(q.getBody())).flatMap(dto -> dto.getOptions().stream()).map(o -> Option.builder().body(o.getBody()).correct(o.getIsCorrect()).question(q).build()).collect(Collectors.toList()));
            }
            optionService.saveAll(options);
        });
        log.info("Quiz has been created with quiz ID : {}", quiz.getId());
        return quiz;
    }

    @Override
    public Quiz update(UpdateQuizDto request, Long quizId, Long userId) {
        log.info("Updating quiz...");
        if (!isUserOwnerOfQuiz(quizId, userId)) {
            log.info("Cannot access quiz with user ID : {}", userId);
            throw new ResponseStatusException(HttpStatus.OK, "Not accessible");
        }
        Quiz quiz = getById(quizId);
        if (quiz.isPublished()) {
            log.info("Quiz with ID : {}, cannot update", quizId);
            throw new ResponseStatusException(HttpStatus.OK, "Quiz is published, cannot be updated");
        }
        if (StringUtils.hasLength(request.getTitle())) {
            quiz.setTitle(request.getTitle());
        }
        if (StringUtils.hasLength(request.getDescription())) {
            quiz.setDescription(request.getDescription());
        }
        if (request.getIsPublished() != null && request.getIsPublished()) {
            quiz.setPublished(true);
        }
        log.info("Quiz has been updated");
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getById(Long id) {
        return quizRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid quiz id"));
    }

    @Override
    public LoadQuizResponseDTO loadQuiz(Long quizId, Long userId) {
        log.info("Loading quiz with ID : {}", quizId);
        Quiz quiz = this.getById(quizId);
        if (quiz.getOwner() != null && quiz.getOwner().getId() == userId) {
            log.info("Cannot attempt self-created quiz. Quiz ID : {}, User ID : {}", userId, quizId);
            throw new ResponseStatusException(HttpStatus.OK, "Cannot attempt self-created quiz");
        }
        if (!quiz.isPublished()) {
            log.info("Quiz not found with ID : {}", quizId);
            throw new ResponseStatusException(HttpStatus.OK, "Quiz not found");
        }
        if (!userQuizAttemptService.hasUserAttemptedQuiz(quizId, userId)) {
            List<QuestionResponseDTO> questions = questionService.getByQuiz(quizId);
            log.info("Quiz Generated for user ID : {}", userId);
            return LoadQuizResponseDTO.builder().title(quiz.getTitle()).description(quiz.getDescription()).numberOfQuestions(quiz.getNumberOfQuestions()).maxMarks(quiz.getMaxMarks()).timeInSeconds(quiz.getTimeInSeconds()).questions(questions).build();
        }
        throw new ResponseStatusException(HttpStatus.OK, "Quiz not found");
    }

    @Override
    public void delete(Long quizId, Long userId) {
        if (isUserOwnerOfQuiz(quizId, userId)) {
            quizRepository.deleteById(quizId);
        }
    }

    @Override
    public QuizResultDTO submitQuiz(Long quizId, List<QuizSubmitDTO> submission, User user) {
        log.info("Submitting quiz and generating result...");
        double totalScore = 0.0;
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not found"));
        List<Long> questionIds = submission.stream().map(QuizSubmitDTO::getQuestionId).collect(Collectors.toList());
        Map<Long, Set<Long>> responses = submission.stream().collect(Collectors.toMap(QuizSubmitDTO::getQuestionId, QuizSubmitDTO::getResponses));
        Map<Question, List<Option>> answers = questionService.getQuestionOptionMap(quizId);
        Map<Long, Integer> optionCountForQuestions = optionService.getOptionCountForQuestions(questionIds);
        List<QuestionAttempt> questionAttempts = new ArrayList<>();
        for (Map.Entry<Question, List<Option>> entry : answers.entrySet()) {
            if(responses.get(entry.getKey().getId())==null){
                continue;
            }
            long qid = entry.getKey().getId();
            int correctOptionsCount = entry.getValue().size();
            double correctOptionWeight = 1.0 / correctOptionsCount;
            double incorrectOptionWeight = entry.getKey().isMultiChoice() ? 1.0 / (optionCountForQuestions.get(entry.getKey().getId()) - correctOptionsCount) : 1;
            double questionScore = 0.0;
            log.info("======================{}",responses.get(entry.getKey().getId()));
            questionAttempts.add(QuestionAttempt.builder().question(entry.getKey()).quiz(quiz).user(user)
                    .response(Set.of(responses.get(entry.getKey().getId()).toArray(new Long[]{}))).build());
            for (Long res : responses.get(qid)) {
                if (entry.getValue().parallelStream().map(Option::getId).collect(Collectors.toList()).contains(res)) {
                    questionScore += correctOptionWeight;
                } else {
                    questionScore -= incorrectOptionWeight;
                }
            }
            totalScore += questionScore;
        }

        answers.forEach((key, value) -> {
            if (!responses.containsKey(key.getId())) {
                questionAttempts.add(QuestionAttempt.builder().question(key).response(Set.of()).quiz(quiz).user(user).build());
            }
        });
        executorService.execute(() -> {
            questionAttemptService.saveAll(questionAttempts);
        });
        UserQuizAttempt userQuizAttempt = userQuizAttemptService.save(UserQuizAttempt.builder().quiz(quiz).user(user).build());
        log.info("Saving result of quiz ID : {} for user ID : {}, score : {}", quizId, user.getId(), totalScore);
        resultService.save(Result.builder().questionsAttempted(responses.keySet().size()).userQuizAttempt(userQuizAttempt).score(totalScore).totalQuestions(quiz.getNumberOfQuestions()).build());
        return QuizResultDTO.builder().quizId(quizId).numberOfQuestions(answers.keySet().size()).attemptedQuestions(responses.keySet().size()).score(totalScore).build();
    }

    private boolean isUserOwnerOfQuiz(Long quizId, Long userId) {
        Quiz quiz = this.getById(quizId);
        return quiz.getOwner().getId() == userId;
    }


    public Map<String, String> publishQuiz(Long quizId, Long userId) {
        log.info("Publishing quiz with ID : {}...", quizId);
        if (!isUserOwnerOfQuiz(quizId, userId)) {
            throw new ResponseStatusException(HttpStatus.OK, "Not accessible");
        }
        Quiz quiz = this.getById(quizId);
        if (!quiz.isPublished()) {
            quiz.setPublished(true);
            quizRepository.save(quiz);
            log.info("Quiz with ID : {} has been published", quizId);
            return Map.of("Response", "SUCCESS");
        }
        log.info("Unexpected Error...");
        return Map.of("Response", "FAILED");
    }
}
