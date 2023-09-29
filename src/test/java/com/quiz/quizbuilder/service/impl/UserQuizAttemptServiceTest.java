package com.quiz.quizbuilder.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quiz.quizbuilder.dto.UserQuizAttemptDTO;
import com.quiz.quizbuilder.entity.Quiz;
import com.quiz.quizbuilder.entity.Result;
import com.quiz.quizbuilder.entity.User;
import com.quiz.quizbuilder.entity.UserQuizAttempt;
import com.quiz.quizbuilder.repository.UserQuizAttemptRepository;
import com.quiz.quizbuilder.service.IResultService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserQuizAttemptService.class})
@ExtendWith(SpringExtension.class)
class UserQuizAttemptServiceTest {
    @MockBean
    private IResultService iResultService;

    @MockBean
    private UserQuizAttemptRepository userQuizAttemptRepository;

    @Autowired
    private UserQuizAttemptService userQuizAttemptService;

    /**
     * Method under test: {@link UserQuizAttemptService#save(UserQuizAttempt)}
     */
    @Test
    void testSave() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("password");
        user1.setUsername("janedoe");

        UserQuizAttempt userQuizAttempt = new UserQuizAttempt();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setQuiz(quiz);
        userQuizAttempt.setUser(user1);
        when(userQuizAttemptRepository.save((UserQuizAttempt) any())).thenReturn(userQuizAttempt);

        User user2 = new User();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreationDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastModifiedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setLastName("Doe");
        user2.setPassword("password");
        user2.setUsername("janedoe");

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
        quiz1.setOwner(user2);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");

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
        user3.setPassword("password");
        user3.setUsername("janedoe");

        UserQuizAttempt userQuizAttempt1 = new UserQuizAttempt();
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt1.setCreationDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt1.setId(123L);
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt1.setLastModifiedDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt1.setQuiz(quiz1);
        userQuizAttempt1.setUser(user3);
        assertSame(userQuizAttempt, userQuizAttemptService.save(userQuizAttempt1));
        verify(userQuizAttemptRepository).save((UserQuizAttempt) any());
    }

    /**
     * Method under test: {@link UserQuizAttemptService#hasUserAttemptedQuiz(Long, Long)}
     */
    @Test
    void testHasUserAttemptedQuiz() {
        when(userQuizAttemptRepository.existsByUserIdAndQuizId((Long) any(), (Long) any())).thenReturn(true);
        assertTrue(userQuizAttemptService.hasUserAttemptedQuiz(123L, 123L));
        verify(userQuizAttemptRepository).existsByUserIdAndQuizId((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link UserQuizAttemptService#hasUserAttemptedQuiz(Long, Long)}
     */
    @Test
    void testHasUserAttemptedQuiz2() {
        when(userQuizAttemptRepository.existsByUserIdAndQuizId((Long) any(), (Long) any())).thenReturn(false);
        assertFalse(userQuizAttemptService.hasUserAttemptedQuiz(123L, 123L));
        verify(userQuizAttemptRepository).existsByUserIdAndQuizId((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link UserQuizAttemptService#getAttemptedUsers(Long)}
     */
    @Test
    void testGetAttemptedUsers() {
        when(iResultService.findByQuiz((Long) any())).thenReturn(new ArrayList<>());
        assertTrue(userQuizAttemptService.getAttemptedUsers(123L).isEmpty());
        verify(iResultService).findByQuiz((Long) any());
    }

    /**
     * Method under test: {@link UserQuizAttemptService#getAttemptedUsers(Long)}
     */
    @Test
    void testGetAttemptedUsers2() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("password");
        user1.setUsername("janedoe");

        UserQuizAttempt userQuizAttempt = new UserQuizAttempt();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setQuiz(quiz);
        userQuizAttempt.setUser(user1);

        Result result = new Result();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result.setCreationDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        result.setId(123L);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result.setLastModifiedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        result.setQuestionsAttempted(1);
        result.setScore(10.0d);
        result.setTotalQuestions(1);
        result.setUserQuizAttempt(userQuizAttempt);

        ArrayList<Result> resultList = new ArrayList<>();
        resultList.add(result);
        when(iResultService.findByQuiz((Long) any())).thenReturn(resultList);
        List<UserQuizAttemptDTO> actualAttemptedUsers = userQuizAttemptService.getAttemptedUsers(123L);
        assertEquals(1, actualAttemptedUsers.size());
        UserQuizAttemptDTO getResult = actualAttemptedUsers.get(0);
        assertEquals(123L, getResult.getQuizId().longValue());
        assertEquals("janedoe", getResult.getUsername());
        assertEquals(123L, getResult.getUserId().longValue());
        assertEquals(10.0d, getResult.getScore().doubleValue());
        verify(iResultService).findByQuiz((Long) any());
    }

    /**
     * Method under test: {@link UserQuizAttemptService#getAttemptedUsers(Long)}
     */
    @Test
    void testGetAttemptedUsers3() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("password");
        user1.setUsername("janedoe");

        UserQuizAttempt userQuizAttempt = new UserQuizAttempt();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setQuiz(quiz);
        userQuizAttempt.setUser(user1);

        Result result = new Result();
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result.setCreationDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        result.setId(123L);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result.setLastModifiedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        result.setQuestionsAttempted(1);
        result.setScore(10.0d);
        result.setTotalQuestions(1);
        result.setUserQuizAttempt(userQuizAttempt);

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

        Quiz quiz1 = new Quiz();
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setCreationDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setDeleted(true);
        quiz1.setDescription("The characteristics of someone or something");
        quiz1.setId(123L);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setLastModifiedDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setMaxMarks(3);
        quiz1.setNumberOfQuestions(10);
        quiz1.setOwner(user2);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");

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

        UserQuizAttempt userQuizAttempt1 = new UserQuizAttempt();
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt1.setCreationDate(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt1.setId(123L);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt1.setLastModifiedDate(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt1.setQuiz(quiz1);
        userQuizAttempt1.setUser(user3);

        Result result1 = new Result();
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result1.setCreationDate(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        result1.setId(123L);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result1.setLastModifiedDate(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        result1.setQuestionsAttempted(1);
        result1.setScore(10.0d);
        result1.setTotalQuestions(1);
        result1.setUserQuizAttempt(userQuizAttempt1);

        ArrayList<Result> resultList = new ArrayList<>();
        resultList.add(result1);
        resultList.add(result);
        when(iResultService.findByQuiz((Long) any())).thenReturn(resultList);
        List<UserQuizAttemptDTO> actualAttemptedUsers = userQuizAttemptService.getAttemptedUsers(123L);
        assertEquals(2, actualAttemptedUsers.size());
        UserQuizAttemptDTO getResult = actualAttemptedUsers.get(0);
        assertEquals("janedoe", getResult.getUsername());
        UserQuizAttemptDTO getResult1 = actualAttemptedUsers.get(1);
        assertEquals("janedoe", getResult1.getUsername());
        assertEquals(123L, getResult1.getUserId().longValue());
        assertEquals(10.0d, getResult1.getScore().doubleValue());
        assertEquals(123L, getResult1.getQuizId().longValue());
        assertEquals(123L, getResult.getUserId().longValue());
        assertEquals(10.0d, getResult.getScore().doubleValue());
        assertEquals(123L, getResult.getQuizId().longValue());
        verify(iResultService).findByQuiz((Long) any());
    }
}

