package com.quiz.quizbuilder.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quiz.quizbuilder.entity.Option;
import com.quiz.quizbuilder.entity.Question;
import com.quiz.quizbuilder.entity.Quiz;
import com.quiz.quizbuilder.entity.User;
import com.quiz.quizbuilder.projection.QuestionOptionCountProjection;
import com.quiz.quizbuilder.repository.OptionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OptionService.class})
@ExtendWith(SpringExtension.class)
class OptionServiceTest {
    @MockBean
    private OptionRepository optionRepository;

    @Autowired
    private OptionService optionService;

    /**
     * Method under test: {@link OptionService#saveAll(List)}
     */
    @Test
    void testSaveAll() {
        ArrayList<Option> optionList = new ArrayList<>();
        when(optionRepository.saveAll((Iterable<Option>) any())).thenReturn(optionList);
        List<Option> actualSaveAllResult = optionService.saveAll(new ArrayList<>());
        assertSame(optionList, actualSaveAllResult);
        assertTrue(actualSaveAllResult.isEmpty());
        verify(optionRepository).saveAll((Iterable<Option>) any());
    }

    /**
     * Method under test: {@link OptionService#update(Option)}
     */
    @Test
    void testUpdate() {
        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreationDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setDeleted(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastModifiedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");

        Quiz quiz = new Quiz();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setCreationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setDeleted(true);
        quiz.setDescription("The characteristics of someone or something");
        quiz.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setLastModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setMaxMarks(3);
        quiz.setNumberOfQuestions(10);
        quiz.setOwner(user);
        quiz.setPublished(true);
        quiz.setTimeInSeconds(10L);
        quiz.setTitle("Dr");

        Question question = new Question();
        question.setBody("Not all who wander are lost");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        question.setDeleted(true);
        question.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        question.setMultiChoice(true);
        question.setQuiz(quiz);

        Option option = new Option();
        option.setBody("Not all who wander are lost");
        option.setCorrect(true);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        option.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        option.setDeleted(true);
        option.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        option.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        option.setQuestion(question);
        when(optionRepository.save((Option) any())).thenReturn(option);

        User user1 = new User();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        Quiz quiz1 = new Quiz();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setCreationDate(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setDeleted(true);
        quiz1.setDescription("The characteristics of someone or something");
        quiz1.setId(123L);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setLastModifiedDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setMaxMarks(3);
        quiz1.setNumberOfQuestions(10);
        quiz1.setOwner(user1);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");

        Question question1 = new Question();
        question1.setBody("Not all who wander are lost");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question1.setCreationDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        question1.setDeleted(true);
        question1.setId(123L);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question1.setLastModifiedDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        question1.setMultiChoice(true);
        question1.setQuiz(quiz1);

        Option option1 = new Option();
        option1.setBody("Not all who wander are lost");
        option1.setCorrect(true);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        option1.setCreationDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        option1.setDeleted(true);
        option1.setId(123L);
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        option1.setLastModifiedDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        option1.setQuestion(question1);
        assertSame(option, optionService.update(option1));
        verify(optionRepository).save((Option) any());
    }

    /**
     * Method under test: {@link OptionService#getById(Long)}
     */
    @Test
    void testGetById() {
        User user = new User();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreationDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setDeleted(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastModifiedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");

        Quiz quiz = new Quiz();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setCreationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setDeleted(true);
        quiz.setDescription("The characteristics of someone or something");
        quiz.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setLastModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setMaxMarks(3);
        quiz.setNumberOfQuestions(10);
        quiz.setOwner(user);
        quiz.setPublished(true);
        quiz.setTimeInSeconds(10L);
        quiz.setTitle("Dr");

        Question question = new Question();
        question.setBody("Not all who wander are lost");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        question.setDeleted(true);
        question.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        question.setMultiChoice(true);
        question.setQuiz(quiz);

        Option option = new Option();
        option.setBody("Not all who wander are lost");
        option.setCorrect(true);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        option.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        option.setDeleted(true);
        option.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        option.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        option.setQuestion(question);
        Optional<Option> ofResult = Optional.of(option);
        when(optionRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(option, optionService.getById(123L));
        verify(optionRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link OptionService#delete(Long)}
     */
    @Test
    void testDelete() {
        doNothing().when(optionRepository).deleteById((Long) any());
        optionService.delete(123L);
        verify(optionRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link OptionService#getOptionCountForQuestions(List)}
     */
    @Test
    void testGetOptionCountForQuestions() {
        when(optionRepository.getOptionCountForQuestions((List<Long>) any())).thenReturn(new ArrayList<>());
        assertTrue(optionService.getOptionCountForQuestions(new ArrayList<>()).isEmpty());
        verify(optionRepository).getOptionCountForQuestions((List<Long>) any());
    }


    /**
     * Method under test: {@link OptionService#getOptionCountForQuestions(List)}
     */
    @Test
    void testGetOptionCountForQuestions3() {
        QuestionOptionCountProjection questionOptionCountProjection = mock(QuestionOptionCountProjection.class);
        when(questionOptionCountProjection.getOptionCount()).thenReturn(3);
        when(questionOptionCountProjection.getQuestionId()).thenReturn(123L);

        ArrayList<QuestionOptionCountProjection> questionOptionCountProjectionList = new ArrayList<>();
        questionOptionCountProjectionList.add(questionOptionCountProjection);
        when(optionRepository.getOptionCountForQuestions((List<Long>) any()))
                .thenReturn(questionOptionCountProjectionList);
        Map<Long, Integer> actualOptionCountForQuestions = optionService.getOptionCountForQuestions(new ArrayList<>());
        assertEquals(1, actualOptionCountForQuestions.size());
        Integer expectedGetResult = new Integer(3);
        assertEquals(expectedGetResult, actualOptionCountForQuestions.get(123L));
        verify(optionRepository).getOptionCountForQuestions((List<Long>) any());
        verify(questionOptionCountProjection).getOptionCount();
        verify(questionOptionCountProjection).getQuestionId();
    }
}

