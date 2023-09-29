package com.quiz.quizbuilder.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quiz.quizbuilder.entity.Question;
import com.quiz.quizbuilder.entity.QuestionAttempt;
import com.quiz.quizbuilder.entity.Quiz;
import com.quiz.quizbuilder.entity.User;
import com.quiz.quizbuilder.repository.QuestionAttemptRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {QuestionAttemptService.class})
@ExtendWith(SpringExtension.class)
class QuestionAttemptServiceTest {
    @MockBean
    private QuestionAttemptRepository questionAttemptRepository;

    @Autowired
    private QuestionAttemptService questionAttemptService;

    /**
     * Method under test: {@link QuestionAttemptService#create(QuestionAttempt)}
     */
    @Test
    void testCreate() {
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
        user.setPassword("password");
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
        user1.setPassword("password");
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
        user2.setPassword("password");
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
        when(questionAttemptRepository.save((QuestionAttempt) any())).thenReturn(questionAttempt);

        User user3 = new User();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreationDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setDeleted(true);
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setLastModifiedDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setLastName("Doe");
        user3.setPassword("password");
        user3.setUsername("janedoe");

        Quiz quiz2 = new Quiz();
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz2.setCreationDate(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        quiz2.setDeleted(true);
        quiz2.setDescription("The characteristics of someone or something");
        quiz2.setId(123L);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz2.setLastModifiedDate(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        quiz2.setMaxMarks(3);
        quiz2.setNumberOfQuestions(10);
        quiz2.setOwner(user3);
        quiz2.setPublished(true);
        quiz2.setTimeInSeconds(10L);
        quiz2.setTitle("Dr");

        Question question1 = new Question();
        question1.setBody("Not all who wander are lost");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question1.setCreationDate(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        question1.setDeleted(true);
        question1.setId(123L);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        question1.setLastModifiedDate(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        question1.setMultiChoice(true);
        question1.setQuiz(quiz2);

        User user4 = new User();
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setCreationDate(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        user4.setDeleted(true);
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(123L);
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setLastModifiedDate(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        user4.setLastName("Doe");
        user4.setPassword("password");
        user4.setUsername("janedoe");

        Quiz quiz3 = new Quiz();
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz3.setCreationDate(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        quiz3.setDeleted(true);
        quiz3.setDescription("The characteristics of someone or something");
        quiz3.setId(123L);
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz3.setLastModifiedDate(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        quiz3.setMaxMarks(3);
        quiz3.setNumberOfQuestions(10);
        quiz3.setOwner(user4);
        quiz3.setPublished(true);
        quiz3.setTimeInSeconds(10L);
        quiz3.setTitle("Dr");

        User user5 = new User();
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user5.setCreationDate(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        user5.setDeleted(true);
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setId(123L);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user5.setLastModifiedDate(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        user5.setLastName("Doe");
        user5.setPassword("password");
        user5.setUsername("janedoe");

        QuestionAttempt questionAttempt1 = new QuestionAttempt();
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        questionAttempt1.setCreationDate(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        questionAttempt1.setId(123L);
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        questionAttempt1.setLastModifiedDate(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        questionAttempt1.setQuestion(question1);
        questionAttempt1.setQuiz(quiz3);
        questionAttempt1.setResponse(new HashSet<>());
        questionAttempt1.setUser(user5);
        assertSame(questionAttempt, questionAttemptService.create(questionAttempt1));
        verify(questionAttemptRepository).save((QuestionAttempt) any());
    }

    /**
     * Method under test: {@link QuestionAttemptService#getById(Long)}
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
        user.setPassword("password");
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
        user1.setPassword("password");
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
        user2.setPassword("password");
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
        Optional<QuestionAttempt> ofResult = Optional.of(questionAttempt);
        when(questionAttemptRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(questionAttempt, questionAttemptService.getById(123L));
        verify(questionAttemptRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link QuestionAttemptService#findByUserQuestionQuiz(Long, Long, Long)}
     */
    @Test
    void testFindByUserQuestionQuiz() {
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
        user.setPassword("password");
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
        user1.setPassword("password");
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
        user2.setPassword("password");
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
        when(questionAttemptRepository.findByUserIdAndQuestionIdAndQuizId(anyLong(), anyLong(), anyLong()))
                .thenReturn(questionAttempt);
        assertSame(questionAttempt, questionAttemptService.findByUserQuestionQuiz(123L, 123L, 123L));
        verify(questionAttemptRepository).findByUserIdAndQuestionIdAndQuizId(anyLong(), anyLong(), anyLong());
    }

    /**
     * Method under test: {@link QuestionAttemptService#saveAll(List)}
     */
    @Test
    void testSaveAll() {
        ArrayList<QuestionAttempt> questionAttemptList = new ArrayList<>();
        when(questionAttemptRepository.saveAll((Iterable<QuestionAttempt>) any())).thenReturn(questionAttemptList);
        List<QuestionAttempt> actualSaveAllResult = questionAttemptService.saveAll(new ArrayList<>());
        assertSame(questionAttemptList, actualSaveAllResult);
        assertTrue(actualSaveAllResult.isEmpty());
        verify(questionAttemptRepository).saveAll((Iterable<QuestionAttempt>) any());
    }

    /**
     * Method under test: {@link QuestionAttemptService#findByUserAndQuiz(Long, Long)}
     */
    @Test
    void testFindByUserAndQuiz() {
        ArrayList<QuestionAttempt> questionAttemptList = new ArrayList<>();
        when(questionAttemptRepository.findByUser_IdAndQuiz_Id(anyLong(), anyLong())).thenReturn(questionAttemptList);
        List<QuestionAttempt> actualFindByUserAndQuizResult = questionAttemptService.findByUserAndQuiz(123L, 123L);
        assertSame(questionAttemptList, actualFindByUserAndQuizResult);
        assertTrue(actualFindByUserAndQuizResult.isEmpty());
        verify(questionAttemptRepository).findByUser_IdAndQuiz_Id(anyLong(), anyLong());
    }
}

