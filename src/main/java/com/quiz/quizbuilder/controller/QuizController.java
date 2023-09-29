package com.quiz.quizbuilder.controller;

import com.quiz.quizbuilder.entity.Quiz;
import com.quiz.quizbuilder.service.IUserQuizAttemptService;
import com.quiz.quizbuilder.service.IUserService;
import com.quiz.quizbuilder.dto.AddQuizRequestDTO;
import com.quiz.quizbuilder.dto.QuizResultDTO;
import com.quiz.quizbuilder.dto.QuizSubmitDTO;
import com.quiz.quizbuilder.dto.UpdateQuizDto;
import com.quiz.quizbuilder.dto.UserQuizAttemptDTO;
import com.quiz.quizbuilder.service.IQuizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
@Api(value = "Quiz API", tags = "Quiz Apis")
public class QuizController {

    private final IQuizService quizService;
    private final IUserService userService;
    private final IUserQuizAttemptService userQuizAttemptService;

    @PostMapping
    @ApiOperation(value = "Create a new quiz", authorizations = @Authorization(value = "basicAuth"))
    public ResponseEntity<Quiz> createQuiz(@RequestBody AddQuizRequestDTO addQuizRequestDTO) {
        return ResponseEntity.status(201).body(quizService.create(addQuizRequestDTO, userService.retrieveUser()));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update existing quiz", authorizations = @Authorization(value = "basicAuth"))
    public ResponseEntity<Quiz> updateQuiz(@RequestBody UpdateQuizDto updateQuizDto, @PathVariable("id") Long quizId) {
        return ResponseEntity.status(200).body(quizService.update(updateQuizDto, quizId, userService.retrieveUser().getId()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Load quiz for user", authorizations = @Authorization(value = "basicAuth"))
    public ResponseEntity<?> loadQuiz(@PathVariable("id") Long quizId) {
        return ResponseEntity.ok(quizService.loadQuiz(quizId, userService.retrieveUser().getId()));
    }

    @PostMapping("/{id}/submit")
    @ApiOperation(value = "Submit Quiz", authorizations = @Authorization(value = "basicAuth"))
    public ResponseEntity<QuizResultDTO> submitQuiz(@PathVariable("id") Long quizId, @RequestBody List<QuizSubmitDTO> submission) {
        return ResponseEntity.ok(quizService.submitQuiz(quizId, submission, userService.retrieveUser()));
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Publish Quiz", authorizations = @Authorization(value = "basicAuth"))
    public ResponseEntity<Map<String,String>> publishQuiz(@PathVariable("id") Long quizId) {
        return ResponseEntity.ok(quizService.publishQuiz(quizId, userService.retrieveUser().getId()));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Quiz", authorizations = @Authorization(value = "basicAuth"))
    public ResponseEntity<Void> deleteQuiz(@PathVariable("id") Long quizId) {
        quizService.delete(quizId, userService.retrieveUser().getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{quizId}/attempts")
    @ApiOperation(value = "Get Users who attempted the quiz", authorizations = @Authorization(value = "basicAuth"))
    public ResponseEntity<List<UserQuizAttemptDTO>> getUserAttemptsByQuiz(@PathVariable("quizId") Long quizId){
        return ResponseEntity.ok(userQuizAttemptService.getAttemptedUsers(quizId));
    }
}
