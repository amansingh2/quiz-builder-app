package com.quiz.quizbuilder.controller;


import com.quiz.quizbuilder.dto.DetailedResultDTO;
import com.quiz.quizbuilder.service.IDetailedResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/result")
@Api(value = "Result API", tags = "Result Apis")
public class ResultController {

    private final IDetailedResultService detailedResultService;

    @GetMapping("/{quizId}/{userId}")
    @ApiOperation(value = "Get Detailed Response",notes = "Get Detailed Response of User on Quiz", authorizations = @Authorization(value = "basicAuth"))
    public ResponseEntity<DetailedResultDTO> getDetailedResult(@PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(detailedResultService.generate(quizId, userId));
    }

}
