package com.quiz.quizbuilder.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quiz.quizbuilder.dto.DetailedQuestionResponseDTO;
import com.quiz.quizbuilder.dto.OptionDto;
import com.quiz.quizbuilder.dto.QuestionDto;
import com.quiz.quizbuilder.entity.Question;
import com.quiz.quizbuilder.entity.QuestionAttempt;
import com.quiz.quizbuilder.entity.Quiz;
import com.quiz.quizbuilder.entity.User;
import com.quiz.quizbuilder.service.IOptionService;
import com.quiz.quizbuilder.service.IQuestionAttemptService;
import com.quiz.quizbuilder.service.IQuestionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DetailedResultService.class})
@ExtendWith(SpringExtension.class)
class DetailedResultServiceTest {
    @Autowired
    private DetailedResultService detailedResultService;

    @MockBean
    private IOptionService iOptionService;

    @MockBean
    private IQuestionAttemptService iQuestionAttemptService;

    @MockBean
    private IQuestionService iQuestionService;

    /**
     * Method under test: {@link DetailedResultService#generate(Long, Long)}
     */
    @Test
    void testGenerate() {
        ArrayList<QuestionAttempt> questionAttemptList = new ArrayList<>();
        when(iQuestionAttemptService.findByUserAndQuiz((Long) any(), (Long) any())).thenReturn(questionAttemptList);
        when(iQuestionService.getQuestionOptionMap((Long) any())).thenReturn(new HashMap<>());
        assertEquals(questionAttemptList, detailedResultService.generate(123L, 123L).getDetailedQuestionResponse());
        verify(iQuestionAttemptService).findByUserAndQuiz((Long) any(), (Long) any());
        verify(iQuestionService).getQuestionOptionMap((Long) any());
    }

    /**
     * Method under test: {@link DetailedResultService#generate(Long, Long)}
     */
    @Test
    void testGenerate2() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        Quiz quiz1 = new Quiz();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setCreationDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setDeleted(true);
        quiz1.setDescription("The characteristics of someone or something");
        quiz1.setId(123L);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setLastModifiedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setMaxMarks(3);
        quiz1.setNumberOfQuestions(10);
        quiz1.setOwner(user1);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");

        User user2 = new User();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreationDate(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastModifiedDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

        QuestionAttempt questionAttempt = new QuestionAttempt();
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        questionAttempt.setCreationDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        questionAttempt.setId(123L);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        questionAttempt.setLastModifiedDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        questionAttempt.setQuestion(question);
        questionAttempt.setQuiz(quiz1);
        questionAttempt.setResponse(new HashSet<>());
        questionAttempt.setUser(user2);

        ArrayList<QuestionAttempt> questionAttemptList = new ArrayList<>();
        questionAttemptList.add(questionAttempt);
        when(iQuestionAttemptService.findByUserAndQuiz((Long) any(), (Long) any())).thenReturn(questionAttemptList);
        when(iQuestionService.getQuestionOptionMap((Long) any())).thenReturn(new HashMap<>());
        List<DetailedQuestionResponseDTO> detailedQuestionResponse = detailedResultService.generate(123L, 123L)
                .getDetailedQuestionResponse();
        assertEquals(1, detailedQuestionResponse.size());
        DetailedQuestionResponseDTO getResult = detailedQuestionResponse.get(0);
        assertTrue(getResult.getIsMultiChoice());
        List<OptionDto> responses = getResult.getResponses();
        assertTrue(responses.isEmpty());
        assertEquals(responses, getResult.getOptions());
        QuestionDto question1 = getResult.getQuestion();
        assertEquals(123L, question1.getId());
        assertEquals("Not all who wander are lost", question1.getBody());
        verify(iQuestionAttemptService).findByUserAndQuiz((Long) any(), (Long) any());
        verify(iQuestionService).getQuestionOptionMap((Long) any());
    }

    /**
     * Method under test: {@link DetailedResultService#generate(Long, Long)}
     */
    @Test
    void testGenerate3() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        Quiz quiz1 = new Quiz();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setCreationDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setDeleted(true);
        quiz1.setDescription("The characteristics of someone or something");
        quiz1.setId(123L);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setLastModifiedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setMaxMarks(3);
        quiz1.setNumberOfQuestions(10);
        quiz1.setOwner(user1);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");

        User user2 = new User();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreationDate(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastModifiedDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

        User user3 = new User();
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreationDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setDeleted(true);
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setLastModifiedDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");

        Quiz quiz2 = new Quiz();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz2.setCreationDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        quiz2.setDeleted(true);
        quiz2.setDescription("The characteristics of someone or something");
        quiz2.setId(123L);
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz2.setLastModifiedDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        quiz2.setMaxMarks(3);
        quiz2.setNumberOfQuestions(10);
        quiz2.setOwner(user3);
        quiz2.setPublished(true);
        quiz2.setTimeInSeconds(10L);
        quiz2.setTitle("Dr");
        Question question1 = mock(Question.class);
        when(question1.isMultiChoice()).thenReturn(true);
        when(question1.getBody()).thenReturn("Not all who wander are lost");
        when(question1.getId()).thenReturn(123L);
        doNothing().when(question1).setCreationDate((Date) any());
        doNothing().when(question1).setLastModifiedDate((Date) any());
        doNothing().when(question1).setBody((String) any());
        doNothing().when(question1).setDeleted(anyBoolean());
        doNothing().when(question1).setId(anyLong());
        doNothing().when(question1).setMultiChoice(anyBoolean());
        doNothing().when(question1).setQuiz((Quiz) any());
        question1.setBody("Not all who wander are lost");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question1.setCreationDate(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        question1.setDeleted(true);
        question1.setId(123L);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question1.setLastModifiedDate(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        question1.setMultiChoice(true);
        question1.setQuiz(quiz2);
        QuestionAttempt questionAttempt = mock(QuestionAttempt.class);
        when(questionAttempt.getQuestion()).thenReturn(question1);
        when(questionAttempt.getResponse()).thenReturn(new HashSet<>());
        doNothing().when(questionAttempt).setCreationDate((Date) any());
        doNothing().when(questionAttempt).setLastModifiedDate((Date) any());
        doNothing().when(questionAttempt).setId(anyLong());
        doNothing().when(questionAttempt).setQuestion((Question) any());
        doNothing().when(questionAttempt).setQuiz((Quiz) any());
        doNothing().when(questionAttempt).setResponse((Set<Long>) any());
        doNothing().when(questionAttempt).setUser((User) any());
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        questionAttempt.setCreationDate(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        questionAttempt.setId(123L);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        questionAttempt.setLastModifiedDate(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        questionAttempt.setQuestion(question);
        questionAttempt.setQuiz(quiz1);
        questionAttempt.setResponse(new HashSet<>());
        questionAttempt.setUser(user2);

        ArrayList<QuestionAttempt> questionAttemptList = new ArrayList<>();
        questionAttemptList.add(questionAttempt);
        when(iQuestionAttemptService.findByUserAndQuiz((Long) any(), (Long) any())).thenReturn(questionAttemptList);
        when(iQuestionService.getQuestionOptionMap((Long) any())).thenReturn(new HashMap<>());
        List<DetailedQuestionResponseDTO> detailedQuestionResponse = detailedResultService.generate(123L, 123L)
                .getDetailedQuestionResponse();
        assertEquals(1, detailedQuestionResponse.size());
        DetailedQuestionResponseDTO getResult = detailedQuestionResponse.get(0);
        assertTrue(getResult.getIsMultiChoice());
        List<OptionDto> responses = getResult.getResponses();
        assertTrue(responses.isEmpty());
        assertEquals(responses, getResult.getOptions());
        QuestionDto question2 = getResult.getQuestion();
        assertEquals(123L, question2.getId());
        assertEquals("Not all who wander are lost", question2.getBody());
        verify(iQuestionAttemptService).findByUserAndQuiz((Long) any(), (Long) any());
        verify(questionAttempt, atLeast(1)).getQuestion();
        verify(questionAttempt).getResponse();
        verify(questionAttempt).setCreationDate((Date) any());
        verify(questionAttempt).setLastModifiedDate((Date) any());
        verify(questionAttempt).setId(anyLong());
        verify(questionAttempt).setQuestion((Question) any());
        verify(questionAttempt).setQuiz((Quiz) any());
        verify(questionAttempt).setResponse((Set<Long>) any());
        verify(questionAttempt).setUser((User) any());
        verify(question1).isMultiChoice();
        verify(question1).getBody();
        verify(question1).getId();
        verify(question1).setCreationDate((Date) any());
        verify(question1).setLastModifiedDate((Date) any());
        verify(question1).setBody((String) any());
        verify(question1).setDeleted(anyBoolean());
        verify(question1).setId(anyLong());
        verify(question1).setMultiChoice(anyBoolean());
        verify(question1).setQuiz((Quiz) any());
        verify(iQuestionService).getQuestionOptionMap((Long) any());
    }

    /**
     * Method under test: {@link DetailedResultService#generate(Long, Long)}
     */
    @Test
    void testGenerate4() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        Quiz quiz1 = new Quiz();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setCreationDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setDeleted(true);
        quiz1.setDescription("The characteristics of someone or something");
        quiz1.setId(123L);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setLastModifiedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setMaxMarks(3);
        quiz1.setNumberOfQuestions(10);
        quiz1.setOwner(user1);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");

        User user2 = new User();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreationDate(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastModifiedDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

        User user3 = new User();
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreationDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setDeleted(true);
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setLastModifiedDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");

        Quiz quiz2 = new Quiz();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz2.setCreationDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        quiz2.setDeleted(true);
        quiz2.setDescription("The characteristics of someone or something");
        quiz2.setId(123L);
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz2.setLastModifiedDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        quiz2.setMaxMarks(3);
        quiz2.setNumberOfQuestions(10);
        quiz2.setOwner(user3);
        quiz2.setPublished(true);
        quiz2.setTimeInSeconds(10L);
        quiz2.setTitle("Dr");
        Question question1 = mock(Question.class);
        when(question1.isMultiChoice()).thenReturn(false);
        when(question1.getBody()).thenReturn("Not all who wander are lost");
        when(question1.getId()).thenReturn(123L);
        doNothing().when(question1).setCreationDate((Date) any());
        doNothing().when(question1).setLastModifiedDate((Date) any());
        doNothing().when(question1).setBody((String) any());
        doNothing().when(question1).setDeleted(anyBoolean());
        doNothing().when(question1).setId(anyLong());
        doNothing().when(question1).setMultiChoice(anyBoolean());
        doNothing().when(question1).setQuiz((Quiz) any());
        question1.setBody("Not all who wander are lost");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question1.setCreationDate(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        question1.setDeleted(true);
        question1.setId(123L);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question1.setLastModifiedDate(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        question1.setMultiChoice(true);
        question1.setQuiz(quiz2);
        QuestionAttempt questionAttempt = mock(QuestionAttempt.class);
        when(questionAttempt.getQuestion()).thenReturn(question1);
        when(questionAttempt.getResponse()).thenReturn(new HashSet<>());
        doNothing().when(questionAttempt).setCreationDate((Date) any());
        doNothing().when(questionAttempt).setLastModifiedDate((Date) any());
        doNothing().when(questionAttempt).setId(anyLong());
        doNothing().when(questionAttempt).setQuestion((Question) any());
        doNothing().when(questionAttempt).setQuiz((Quiz) any());
        doNothing().when(questionAttempt).setResponse((Set<Long>) any());
        doNothing().when(questionAttempt).setUser((User) any());
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        questionAttempt.setCreationDate(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        questionAttempt.setId(123L);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        questionAttempt.setLastModifiedDate(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        questionAttempt.setQuestion(question);
        questionAttempt.setQuiz(quiz1);
        questionAttempt.setResponse(new HashSet<>());
        questionAttempt.setUser(user2);

        ArrayList<QuestionAttempt> questionAttemptList = new ArrayList<>();
        questionAttemptList.add(questionAttempt);
        when(iQuestionAttemptService.findByUserAndQuiz((Long) any(), (Long) any())).thenReturn(questionAttemptList);
        when(iQuestionService.getQuestionOptionMap((Long) any())).thenReturn(new HashMap<>());
        List<DetailedQuestionResponseDTO> detailedQuestionResponse = detailedResultService.generate(123L, 123L)
                .getDetailedQuestionResponse();
        assertEquals(1, detailedQuestionResponse.size());
        DetailedQuestionResponseDTO getResult = detailedQuestionResponse.get(0);
        assertFalse(getResult.getIsMultiChoice());
        List<OptionDto> responses = getResult.getResponses();
        assertTrue(responses.isEmpty());
        assertEquals(responses, getResult.getOptions());
        QuestionDto question2 = getResult.getQuestion();
        assertEquals(123L, question2.getId());
        assertEquals("Not all who wander are lost", question2.getBody());
        verify(iQuestionAttemptService).findByUserAndQuiz((Long) any(), (Long) any());
        verify(questionAttempt, atLeast(1)).getQuestion();
        verify(questionAttempt).getResponse();
        verify(questionAttempt).setCreationDate((Date) any());
        verify(questionAttempt).setLastModifiedDate((Date) any());
        verify(questionAttempt).setId(anyLong());
        verify(questionAttempt).setQuestion((Question) any());
        verify(questionAttempt).setQuiz((Quiz) any());
        verify(questionAttempt).setResponse((Set<Long>) any());
        verify(questionAttempt).setUser((User) any());
        verify(question1).isMultiChoice();
        verify(question1).getBody();
        verify(question1).getId();
        verify(question1).setCreationDate((Date) any());
        verify(question1).setLastModifiedDate((Date) any());
        verify(question1).setBody((String) any());
        verify(question1).setDeleted(anyBoolean());
        verify(question1).setId(anyLong());
        verify(question1).setMultiChoice(anyBoolean());
        verify(question1).setQuiz((Quiz) any());
        verify(iQuestionService).getQuestionOptionMap((Long) any());
    }
}

