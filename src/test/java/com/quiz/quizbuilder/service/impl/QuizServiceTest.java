package com.quiz.quizbuilder.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quiz.quizbuilder.dto.AddQuizRequestDTO;
import com.quiz.quizbuilder.dto.QuizSubmitDTO;
import com.quiz.quizbuilder.dto.UpdateQuizDto;
import com.quiz.quizbuilder.entity.Quiz;
import com.quiz.quizbuilder.entity.Result;
import com.quiz.quizbuilder.entity.User;
import com.quiz.quizbuilder.entity.UserQuizAttempt;
import com.quiz.quizbuilder.repository.QuizRepository;
import com.quiz.quizbuilder.service.IOptionService;
import com.quiz.quizbuilder.service.IQuestionAttemptService;
import com.quiz.quizbuilder.service.IQuestionService;
import com.quiz.quizbuilder.service.IResultService;
import com.quiz.quizbuilder.service.IUserQuizAttemptService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {QuizService.class})
@ExtendWith(SpringExtension.class)
class QuizServiceTest {
    @MockBean
    private ExecutorService executorService;

    @MockBean
    private IOptionService iOptionService;

    @MockBean
    private IQuestionAttemptService iQuestionAttemptService;

    @MockBean
    private IQuestionService iQuestionService;

    @MockBean
    private IResultService iResultService;

    @MockBean
    private IUserQuizAttemptService iUserQuizAttemptService;

    @MockBean
    private QuizRepository quizRepository;

    @Autowired
    private QuizService quizService;

    /**
     * Method under test: {@link QuizService#create(AddQuizRequestDTO, User)}
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
        when(quizRepository.save((Quiz) any())).thenReturn(quiz);
        doNothing().when(executorService).execute((Runnable) any());

        UpdateQuizDto updateQuizDto = new UpdateQuizDto();
        updateQuizDto.setDescription("The characteristics of someone or something");
        updateQuizDto.setIsPublished(true);
        updateQuizDto.setNumberOfQuestions(10);
        updateQuizDto.setTimeInSeconds(10L);
        updateQuizDto.setTitle("Dr");

        AddQuizRequestDTO addQuizRequestDTO = new AddQuizRequestDTO();
        addQuizRequestDTO.setQuestions(new ArrayList<>());
        addQuizRequestDTO.setQuiz(updateQuizDto);

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
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Quiz actualCreateResult = quizService.create(addQuizRequestDTO, user1);
        assertFalse(actualCreateResult.isPublished());
        assertFalse(actualCreateResult.isDeleted());
        assertEquals("Dr", actualCreateResult.getTitle());
        assertEquals(10L, actualCreateResult.getTimeInSeconds());
        assertEquals(user, actualCreateResult.getOwner());
        assertEquals(10, actualCreateResult.getNumberOfQuestions());
        assertEquals("The characteristics of someone or something", actualCreateResult.getDescription());
        assertEquals(10, actualCreateResult.getMaxMarks());
        assertEquals(0L, actualCreateResult.getId());
        verify(quizRepository).save((Quiz) any());
        verify(executorService).execute((Runnable) any());
    }

    /**
     * Method under test: {@link QuizService#create(AddQuizRequestDTO, User)}
     */
    @Test
    void testCreate2() {
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
        when(quizRepository.save((Quiz) any())).thenReturn(quiz);
        doThrow(new ResponseStatusException(HttpStatus.CONTINUE)).when(executorService).execute((Runnable) any());

        UpdateQuizDto updateQuizDto = new UpdateQuizDto();
        updateQuizDto.setDescription("The characteristics of someone or something");
        updateQuizDto.setIsPublished(true);
        updateQuizDto.setNumberOfQuestions(10);
        updateQuizDto.setTimeInSeconds(10L);
        updateQuizDto.setTitle("Dr");

        AddQuizRequestDTO addQuizRequestDTO = new AddQuizRequestDTO();
        addQuizRequestDTO.setQuestions(new ArrayList<>());
        addQuizRequestDTO.setQuiz(updateQuizDto);

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
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        assertThrows(ResponseStatusException.class, () -> quizService.create(addQuizRequestDTO, user1));
        verify(quizRepository).save((Quiz) any());
        verify(executorService).execute((Runnable) any());
    }

    /**
     * Method under test: {@link QuizService#update(UpdateQuizDto, Long, Long)}
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
        Optional<Quiz> ofResult = Optional.of(quiz);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);

        UpdateQuizDto updateQuizDto = new UpdateQuizDto();
        updateQuizDto.setDescription("The characteristics of someone or something");
        updateQuizDto.setIsPublished(true);
        updateQuizDto.setNumberOfQuestions(10);
        updateQuizDto.setTimeInSeconds(10L);
        updateQuizDto.setTitle("Dr");
        assertThrows(ResponseStatusException.class, () -> quizService.update(updateQuizDto, 123L, 123L));
        verify(quizRepository, atLeast(1)).findById((Long) any());
    }

    /**
     * Method under test: {@link QuizService#update(UpdateQuizDto, Long, Long)}
     */
    @Test
    void testUpdate2() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Quiz quiz = mock(Quiz.class);
        when(quiz.isPublished()).thenReturn(true);
        when(quiz.getOwner()).thenReturn(user1);
        doNothing().when(quiz).setCreationDate((Date) any());
        doNothing().when(quiz).setLastModifiedDate((Date) any());
        doNothing().when(quiz).setDeleted(anyBoolean());
        doNothing().when(quiz).setDescription((String) any());
        doNothing().when(quiz).setId(anyLong());
        doNothing().when(quiz).setMaxMarks(anyInt());
        doNothing().when(quiz).setNumberOfQuestions(anyInt());
        doNothing().when(quiz).setOwner((User) any());
        doNothing().when(quiz).setPublished(anyBoolean());
        doNothing().when(quiz).setTimeInSeconds(anyLong());
        doNothing().when(quiz).setTitle((String) any());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setDeleted(true);
        quiz.setDescription("The characteristics of someone or something");
        quiz.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setMaxMarks(3);
        quiz.setNumberOfQuestions(10);
        quiz.setOwner(user);
        quiz.setPublished(true);
        quiz.setTimeInSeconds(10L);
        quiz.setTitle("Dr");
        Optional<Quiz> ofResult = Optional.of(quiz);

        User user2 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

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
        quiz1.setOwner(user2);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");
        when(quizRepository.save((Quiz) any())).thenReturn(quiz1);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);

        UpdateQuizDto updateQuizDto = new UpdateQuizDto();
        updateQuizDto.setDescription("The characteristics of someone or something");
        updateQuizDto.setIsPublished(true);
        updateQuizDto.setNumberOfQuestions(10);
        updateQuizDto.setTimeInSeconds(10L);
        updateQuizDto.setTitle("Dr");
        assertThrows(ResponseStatusException.class, () -> quizService.update(updateQuizDto, 123L, 123L));
        verify(quizRepository, atLeast(1)).findById((Long) any());
        verify(quiz).isPublished();
        verify(quiz).getOwner();
        verify(quiz).setCreationDate((Date) any());
        verify(quiz).setLastModifiedDate((Date) any());
        verify(quiz).setDeleted(anyBoolean());
        verify(quiz).setDescription((String) any());
        verify(quiz).setId(anyLong());
        verify(quiz).setMaxMarks(anyInt());
        verify(quiz).setNumberOfQuestions(anyInt());
        verify(quiz).setOwner((User) any());
        verify(quiz).setPublished(anyBoolean());
        verify(quiz).setTimeInSeconds(anyLong());
        verify(quiz).setTitle((String) any());
    }

    /**
     * Method under test: {@link QuizService#update(UpdateQuizDto, Long, Long)}
     */
    @Test
    void testUpdate3() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Quiz quiz = mock(Quiz.class);
        when(quiz.isPublished()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        when(quiz.getOwner()).thenReturn(user1);
        doNothing().when(quiz).setCreationDate((Date) any());
        doNothing().when(quiz).setLastModifiedDate((Date) any());
        doNothing().when(quiz).setDeleted(anyBoolean());
        doNothing().when(quiz).setDescription((String) any());
        doNothing().when(quiz).setId(anyLong());
        doNothing().when(quiz).setMaxMarks(anyInt());
        doNothing().when(quiz).setNumberOfQuestions(anyInt());
        doNothing().when(quiz).setOwner((User) any());
        doNothing().when(quiz).setPublished(anyBoolean());
        doNothing().when(quiz).setTimeInSeconds(anyLong());
        doNothing().when(quiz).setTitle((String) any());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setDeleted(true);
        quiz.setDescription("The characteristics of someone or something");
        quiz.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setMaxMarks(3);
        quiz.setNumberOfQuestions(10);
        quiz.setOwner(user);
        quiz.setPublished(true);
        quiz.setTimeInSeconds(10L);
        quiz.setTitle("Dr");
        Optional<Quiz> ofResult = Optional.of(quiz);

        User user2 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

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
        quiz1.setOwner(user2);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");
        when(quizRepository.save((Quiz) any())).thenReturn(quiz1);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);

        UpdateQuizDto updateQuizDto = new UpdateQuizDto();
        updateQuizDto.setDescription("The characteristics of someone or something");
        updateQuizDto.setIsPublished(true);
        updateQuizDto.setNumberOfQuestions(10);
        updateQuizDto.setTimeInSeconds(10L);
        updateQuizDto.setTitle("Dr");
        assertThrows(ResponseStatusException.class, () -> quizService.update(updateQuizDto, 123L, 123L));
        verify(quizRepository, atLeast(1)).findById((Long) any());
        verify(quiz).isPublished();
        verify(quiz).getOwner();
        verify(quiz).setCreationDate((Date) any());
        verify(quiz).setLastModifiedDate((Date) any());
        verify(quiz).setDeleted(anyBoolean());
        verify(quiz).setDescription((String) any());
        verify(quiz).setId(anyLong());
        verify(quiz).setMaxMarks(anyInt());
        verify(quiz).setNumberOfQuestions(anyInt());
        verify(quiz).setOwner((User) any());
        verify(quiz).setPublished(anyBoolean());
        verify(quiz).setTimeInSeconds(anyLong());
        verify(quiz).setTitle((String) any());
    }

    /**
     * Method under test: {@link QuizService#getById(Long)}
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
        Optional<Quiz> ofResult = Optional.of(quiz);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(quiz, quizService.getById(123L));
        verify(quizRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link QuizService#getById(Long)}
     */
    @Test
    void testGetById2() {
        when(quizRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> quizService.getById(123L));
        verify(quizRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link QuizService#getById(Long)}
     */
    @Test
    void testGetById3() {
        when(quizRepository.findById((Long) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, () -> quizService.getById(123L));
        verify(quizRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link QuizService#loadQuiz(Long, Long)}
     */
    @Test
    void testLoadQuiz() {
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
        Optional<Quiz> ofResult = Optional.of(quiz);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResponseStatusException.class, () -> quizService.loadQuiz(123L, 123L));
        verify(quizRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link QuizService#loadQuiz(Long, Long)}
     */
    @Test
    void testLoadQuiz2() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Quiz quiz = mock(Quiz.class);
        when(quiz.isPublished()).thenReturn(true);
        when(quiz.getOwner()).thenReturn(user1);
        doNothing().when(quiz).setCreationDate((Date) any());
        doNothing().when(quiz).setLastModifiedDate((Date) any());
        doNothing().when(quiz).setDeleted(anyBoolean());
        doNothing().when(quiz).setDescription((String) any());
        doNothing().when(quiz).setId(anyLong());
        doNothing().when(quiz).setMaxMarks(anyInt());
        doNothing().when(quiz).setNumberOfQuestions(anyInt());
        doNothing().when(quiz).setOwner((User) any());
        doNothing().when(quiz).setPublished(anyBoolean());
        doNothing().when(quiz).setTimeInSeconds(anyLong());
        doNothing().when(quiz).setTitle((String) any());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setDeleted(true);
        quiz.setDescription("The characteristics of someone or something");
        quiz.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setMaxMarks(3);
        quiz.setNumberOfQuestions(10);
        quiz.setOwner(user);
        quiz.setPublished(true);
        quiz.setTimeInSeconds(10L);
        quiz.setTitle("Dr");
        Optional<Quiz> ofResult = Optional.of(quiz);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResponseStatusException.class, () -> quizService.loadQuiz(123L, 123L));
        verify(quizRepository).findById((Long) any());
        verify(quiz, atLeast(1)).getOwner();
        verify(quiz).setCreationDate((Date) any());
        verify(quiz).setLastModifiedDate((Date) any());
        verify(quiz).setDeleted(anyBoolean());
        verify(quiz).setDescription((String) any());
        verify(quiz).setId(anyLong());
        verify(quiz).setMaxMarks(anyInt());
        verify(quiz).setNumberOfQuestions(anyInt());
        verify(quiz).setOwner((User) any());
        verify(quiz).setPublished(anyBoolean());
        verify(quiz).setTimeInSeconds(anyLong());
        verify(quiz).setTitle((String) any());
    }

    /**
     * Method under test: {@link QuizService#loadQuiz(Long, Long)}
     */
    @Test
    void testLoadQuiz3() {
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
        User user1 = mock(User.class);
        when(user1.getId()).thenReturn(1L);
        doNothing().when(user1).setCreationDate((Date) any());
        doNothing().when(user1).setDeleted(anyBoolean());
        doNothing().when(user1).setEmail((String) any());
        doNothing().when(user1).setFirstName((String) any());
        doNothing().when(user1).setId(anyLong());
        doNothing().when(user1).setLastModifiedDate((Date) any());
        doNothing().when(user1).setLastName((String) any());
        doNothing().when(user1).setPassword((String) any());
        doNothing().when(user1).setUsername((String) any());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Quiz quiz = mock(Quiz.class);
        when(quiz.getMaxMarks()).thenReturn(3);
        when(quiz.getNumberOfQuestions()).thenReturn(10);
        when(quiz.getDescription()).thenReturn("The characteristics of someone or something");
        when(quiz.getTitle()).thenReturn("Dr");
        when(quiz.getTimeInSeconds()).thenReturn(10L);
        when(quiz.isPublished()).thenReturn(true);
        when(quiz.getOwner()).thenReturn(user1);
        doNothing().when(quiz).setCreationDate((Date) any());
        doNothing().when(quiz).setLastModifiedDate((Date) any());
        doNothing().when(quiz).setDeleted(anyBoolean());
        doNothing().when(quiz).setDescription((String) any());
        doNothing().when(quiz).setId(anyLong());
        doNothing().when(quiz).setMaxMarks(anyInt());
        doNothing().when(quiz).setNumberOfQuestions(anyInt());
        doNothing().when(quiz).setOwner((User) any());
        doNothing().when(quiz).setPublished(anyBoolean());
        doNothing().when(quiz).setTimeInSeconds(anyLong());
        doNothing().when(quiz).setTitle((String) any());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setDeleted(true);
        quiz.setDescription("The characteristics of someone or something");
        quiz.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setMaxMarks(3);
        quiz.setNumberOfQuestions(10);
        quiz.setOwner(user);
        quiz.setPublished(true);
        quiz.setTimeInSeconds(10L);
        quiz.setTitle("Dr");
        Optional<Quiz> ofResult = Optional.of(quiz);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        when(iQuestionService.getByQuiz((Long) any())).thenReturn(new ArrayList<>());
        when(iUserQuizAttemptService.hasUserAttemptedQuiz((Long) any(), (Long) any())).thenReturn(true);
        assertThrows(ResponseStatusException.class, () -> quizService.loadQuiz(123L, 123L));
        verify(quizRepository).findById((Long) any());
        verify(quiz).isPublished();
        verify(quiz, atLeast(1)).getOwner();
        verify(quiz).setCreationDate((Date) any());
        verify(quiz).setLastModifiedDate((Date) any());
        verify(quiz).setDeleted(anyBoolean());
        verify(quiz).setDescription((String) any());
        verify(quiz).setId(anyLong());
        verify(quiz).setMaxMarks(anyInt());
        verify(quiz).setNumberOfQuestions(anyInt());
        verify(quiz).setOwner((User) any());
        verify(quiz).setPublished(anyBoolean());
        verify(quiz).setTimeInSeconds(anyLong());
        verify(quiz).setTitle((String) any());
        verify(user1).getId();
        verify(user1).setCreationDate((Date) any());
        verify(user1).setDeleted(anyBoolean());
        verify(user1).setEmail((String) any());
        verify(user1).setFirstName((String) any());
        verify(user1).setId(anyLong());
        verify(user1).setLastModifiedDate((Date) any());
        verify(user1).setLastName((String) any());
        verify(user1).setPassword((String) any());
        verify(user1).setUsername((String) any());
        verify(iUserQuizAttemptService).hasUserAttemptedQuiz((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link QuizService#delete(Long, Long)}
     */
    @Test
    void testDelete() {
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
        Optional<Quiz> ofResult = Optional.of(quiz);
        doNothing().when(quizRepository).deleteById((Long) any());
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        quizService.delete(123L, 123L);
        verify(quizRepository).findById((Long) any());
        verify(quizRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link QuizService#delete(Long, Long)}
     */
    @Test
    void testDelete2() {
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
        Optional<Quiz> ofResult = Optional.of(quiz);
        doThrow(new ResponseStatusException(HttpStatus.CONTINUE)).when(quizRepository).deleteById((Long) any());
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResponseStatusException.class, () -> quizService.delete(123L, 123L));
        verify(quizRepository).findById((Long) any());
        verify(quizRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link QuizService#delete(Long, Long)}
     */
    @Test
    void testDelete3() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Quiz quiz = mock(Quiz.class);
        when(quiz.getOwner()).thenReturn(user1);
        doNothing().when(quiz).setCreationDate((Date) any());
        doNothing().when(quiz).setLastModifiedDate((Date) any());
        doNothing().when(quiz).setDeleted(anyBoolean());
        doNothing().when(quiz).setDescription((String) any());
        doNothing().when(quiz).setId(anyLong());
        doNothing().when(quiz).setMaxMarks(anyInt());
        doNothing().when(quiz).setNumberOfQuestions(anyInt());
        doNothing().when(quiz).setOwner((User) any());
        doNothing().when(quiz).setPublished(anyBoolean());
        doNothing().when(quiz).setTimeInSeconds(anyLong());
        doNothing().when(quiz).setTitle((String) any());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setDeleted(true);
        quiz.setDescription("The characteristics of someone or something");
        quiz.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setMaxMarks(3);
        quiz.setNumberOfQuestions(10);
        quiz.setOwner(user);
        quiz.setPublished(true);
        quiz.setTimeInSeconds(10L);
        quiz.setTitle("Dr");
        Optional<Quiz> ofResult = Optional.of(quiz);
        doNothing().when(quizRepository).deleteById((Long) any());
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        quizService.delete(123L, 123L);
        verify(quizRepository).findById((Long) any());
        verify(quizRepository).deleteById((Long) any());
        verify(quiz).getOwner();
        verify(quiz).setCreationDate((Date) any());
        verify(quiz).setLastModifiedDate((Date) any());
        verify(quiz).setDeleted(anyBoolean());
        verify(quiz).setDescription((String) any());
        verify(quiz).setId(anyLong());
        verify(quiz).setMaxMarks(anyInt());
        verify(quiz).setNumberOfQuestions(anyInt());
        verify(quiz).setOwner((User) any());
        verify(quiz).setPublished(anyBoolean());
        verify(quiz).setTimeInSeconds(anyLong());
        verify(quiz).setTitle((String) any());
    }

    /**
     * Method under test: {@link QuizService#delete(Long, Long)}
     */
    @Test
    void testDelete4() {
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
        User user1 = mock(User.class);
        when(user1.getId()).thenReturn(1L);
        doNothing().when(user1).setCreationDate((Date) any());
        doNothing().when(user1).setDeleted(anyBoolean());
        doNothing().when(user1).setEmail((String) any());
        doNothing().when(user1).setFirstName((String) any());
        doNothing().when(user1).setId(anyLong());
        doNothing().when(user1).setLastModifiedDate((Date) any());
        doNothing().when(user1).setLastName((String) any());
        doNothing().when(user1).setPassword((String) any());
        doNothing().when(user1).setUsername((String) any());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Quiz quiz = mock(Quiz.class);
        when(quiz.getOwner()).thenReturn(user1);
        doNothing().when(quiz).setCreationDate((Date) any());
        doNothing().when(quiz).setLastModifiedDate((Date) any());
        doNothing().when(quiz).setDeleted(anyBoolean());
        doNothing().when(quiz).setDescription((String) any());
        doNothing().when(quiz).setId(anyLong());
        doNothing().when(quiz).setMaxMarks(anyInt());
        doNothing().when(quiz).setNumberOfQuestions(anyInt());
        doNothing().when(quiz).setOwner((User) any());
        doNothing().when(quiz).setPublished(anyBoolean());
        doNothing().when(quiz).setTimeInSeconds(anyLong());
        doNothing().when(quiz).setTitle((String) any());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setDeleted(true);
        quiz.setDescription("The characteristics of someone or something");
        quiz.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setMaxMarks(3);
        quiz.setNumberOfQuestions(10);
        quiz.setOwner(user);
        quiz.setPublished(true);
        quiz.setTimeInSeconds(10L);
        quiz.setTitle("Dr");
        Optional<Quiz> ofResult = Optional.of(quiz);
        doNothing().when(quizRepository).deleteById((Long) any());
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        quizService.delete(123L, 123L);
        verify(quizRepository).findById((Long) any());
        verify(quiz).getOwner();
        verify(quiz).setCreationDate((Date) any());
        verify(quiz).setLastModifiedDate((Date) any());
        verify(quiz).setDeleted(anyBoolean());
        verify(quiz).setDescription((String) any());
        verify(quiz).setId(anyLong());
        verify(quiz).setMaxMarks(anyInt());
        verify(quiz).setNumberOfQuestions(anyInt());
        verify(quiz).setOwner((User) any());
        verify(quiz).setPublished(anyBoolean());
        verify(quiz).setTimeInSeconds(anyLong());
        verify(quiz).setTitle((String) any());
        verify(user1).getId();
        verify(user1).setCreationDate((Date) any());
        verify(user1).setDeleted(anyBoolean());
        verify(user1).setEmail((String) any());
        verify(user1).setFirstName((String) any());
        verify(user1).setId(anyLong());
        verify(user1).setLastModifiedDate((Date) any());
        verify(user1).setLastName((String) any());
        verify(user1).setPassword((String) any());
        verify(user1).setUsername((String) any());
    }

    /**
     * Method under test: {@link QuizService#submitQuiz(Long, List, User)}
     */
    @Test
    void testSubmitQuiz() {
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
        Optional<Quiz> ofResult = Optional.of(quiz);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        when(iQuestionService.getQuestionOptionMap((Long) any())).thenReturn(new HashMap<>());
        when(iOptionService.getOptionCountForQuestions((List<Long>) any())).thenReturn(new HashMap<>());

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
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        Quiz quiz1 = new Quiz();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setDeleted(true);
        quiz1.setDescription("The characteristics of someone or something");
        quiz1.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setMaxMarks(3);
        quiz1.setNumberOfQuestions(10);
        quiz1.setOwner(user1);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");

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
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

        UserQuizAttempt userQuizAttempt = new UserQuizAttempt();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setCreationDate(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setId(123L);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setLastModifiedDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setQuiz(quiz1);
        userQuizAttempt.setUser(user2);
        when(iUserQuizAttemptService.save((UserQuizAttempt) any())).thenReturn(userQuizAttempt);
        when(iResultService.save((Result) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        doNothing().when(executorService).execute((Runnable) any());
        ArrayList<QuizSubmitDTO> submission = new ArrayList<>();

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
        assertThrows(ResponseStatusException.class, () -> quizService.submitQuiz(123L, submission, user3));
        verify(quizRepository).findById((Long) any());
        verify(iQuestionService).getQuestionOptionMap((Long) any());
        verify(iOptionService).getOptionCountForQuestions((List<Long>) any());
        verify(iUserQuizAttemptService).save((UserQuizAttempt) any());
        verify(iResultService).save((Result) any());
        verify(executorService).execute((Runnable) any());
    }

    /**
     * Method under test: {@link QuizService#submitQuiz(Long, List, User)}
     */
    @Test
    void testSubmitQuiz2() {
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
        Optional<Quiz> ofResult = Optional.of(quiz);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        when(iQuestionService.getQuestionOptionMap((Long) any())).thenReturn(new HashMap<>());
        when(iOptionService.getOptionCountForQuestions((List<Long>) any())).thenReturn(new HashMap<>());

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
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        Quiz quiz1 = new Quiz();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setDeleted(true);
        quiz1.setDescription("The characteristics of someone or something");
        quiz1.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setMaxMarks(3);
        quiz1.setNumberOfQuestions(10);
        quiz1.setOwner(user1);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");

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
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

        UserQuizAttempt userQuizAttempt = new UserQuizAttempt();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setCreationDate(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setId(123L);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setLastModifiedDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setQuiz(quiz1);
        userQuizAttempt.setUser(user2);
        when(iUserQuizAttemptService.save((UserQuizAttempt) any())).thenReturn(userQuizAttempt);
        when(iResultService.save((Result) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        doThrow(new ResponseStatusException(HttpStatus.CONTINUE)).when(executorService).execute((Runnable) any());
        ArrayList<QuizSubmitDTO> submission = new ArrayList<>();

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
        assertThrows(ResponseStatusException.class, () -> quizService.submitQuiz(123L, submission, user3));
        verify(quizRepository).findById((Long) any());
        verify(iQuestionService).getQuestionOptionMap((Long) any());
        verify(iOptionService).getOptionCountForQuestions((List<Long>) any());
        verify(executorService).execute((Runnable) any());
    }

    /**
     * Method under test: {@link QuizService#submitQuiz(Long, List, User)}
     */
    @Test
    void testSubmitQuiz3() {
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
        Quiz quiz = mock(Quiz.class);
        when(quiz.getNumberOfQuestions()).thenReturn(10);
        doNothing().when(quiz).setCreationDate((Date) any());
        doNothing().when(quiz).setLastModifiedDate((Date) any());
        doNothing().when(quiz).setDeleted(anyBoolean());
        doNothing().when(quiz).setDescription((String) any());
        doNothing().when(quiz).setId(anyLong());
        doNothing().when(quiz).setMaxMarks(anyInt());
        doNothing().when(quiz).setNumberOfQuestions(anyInt());
        doNothing().when(quiz).setOwner((User) any());
        doNothing().when(quiz).setPublished(anyBoolean());
        doNothing().when(quiz).setTimeInSeconds(anyLong());
        doNothing().when(quiz).setTitle((String) any());
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
        Optional<Quiz> ofResult = Optional.of(quiz);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        when(iQuestionService.getQuestionOptionMap((Long) any())).thenReturn(new HashMap<>());
        when(iOptionService.getOptionCountForQuestions((List<Long>) any())).thenReturn(new HashMap<>());

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
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        Quiz quiz1 = new Quiz();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setDeleted(true);
        quiz1.setDescription("The characteristics of someone or something");
        quiz1.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz1.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        quiz1.setMaxMarks(3);
        quiz1.setNumberOfQuestions(10);
        quiz1.setOwner(user1);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");

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
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

        UserQuizAttempt userQuizAttempt = new UserQuizAttempt();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setCreationDate(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setId(123L);
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userQuizAttempt.setLastModifiedDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        userQuizAttempt.setQuiz(quiz1);
        userQuizAttempt.setUser(user2);
        when(iUserQuizAttemptService.save((UserQuizAttempt) any())).thenReturn(userQuizAttempt);
        when(iResultService.save((Result) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        doNothing().when(executorService).execute((Runnable) any());
        ArrayList<QuizSubmitDTO> submission = new ArrayList<>();

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
        assertThrows(ResponseStatusException.class, () -> quizService.submitQuiz(123L, submission, user3));
        verify(quizRepository).findById((Long) any());
        verify(quiz).getNumberOfQuestions();
        verify(quiz).setCreationDate((Date) any());
        verify(quiz).setLastModifiedDate((Date) any());
        verify(quiz).setDeleted(anyBoolean());
        verify(quiz).setDescription((String) any());
        verify(quiz).setId(anyLong());
        verify(quiz).setMaxMarks(anyInt());
        verify(quiz).setNumberOfQuestions(anyInt());
        verify(quiz).setOwner((User) any());
        verify(quiz).setPublished(anyBoolean());
        verify(quiz).setTimeInSeconds(anyLong());
        verify(quiz).setTitle((String) any());
        verify(iQuestionService).getQuestionOptionMap((Long) any());
        verify(iOptionService).getOptionCountForQuestions((List<Long>) any());
        verify(iUserQuizAttemptService).save((UserQuizAttempt) any());
        verify(iResultService).save((Result) any());
        verify(executorService).execute((Runnable) any());
    }

    /**
     * Method under test: {@link QuizService#publishQuiz(Long, Long)}
     */
    @Test
    void testPublishQuiz() {
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
        Optional<Quiz> ofResult = Optional.of(quiz);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        Map<String, String> actualPublishQuizResult = quizService.publishQuiz(123L, 123L);
        assertEquals(1, actualPublishQuizResult.size());
        assertEquals("FAILED", actualPublishQuizResult.get("Response"));
        verify(quizRepository, atLeast(1)).findById((Long) any());
    }

    /**
     * Method under test: {@link QuizService#publishQuiz(Long, Long)}
     */
    @Test
    void testPublishQuiz2() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Quiz quiz = mock(Quiz.class);
        when(quiz.isPublished()).thenReturn(true);
        when(quiz.getOwner()).thenReturn(user1);
        doNothing().when(quiz).setCreationDate((Date) any());
        doNothing().when(quiz).setLastModifiedDate((Date) any());
        doNothing().when(quiz).setDeleted(anyBoolean());
        doNothing().when(quiz).setDescription((String) any());
        doNothing().when(quiz).setId(anyLong());
        doNothing().when(quiz).setMaxMarks(anyInt());
        doNothing().when(quiz).setNumberOfQuestions(anyInt());
        doNothing().when(quiz).setOwner((User) any());
        doNothing().when(quiz).setPublished(anyBoolean());
        doNothing().when(quiz).setTimeInSeconds(anyLong());
        doNothing().when(quiz).setTitle((String) any());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setDeleted(true);
        quiz.setDescription("The characteristics of someone or something");
        quiz.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setMaxMarks(3);
        quiz.setNumberOfQuestions(10);
        quiz.setOwner(user);
        quiz.setPublished(true);
        quiz.setTimeInSeconds(10L);
        quiz.setTitle("Dr");
        Optional<Quiz> ofResult = Optional.of(quiz);

        User user2 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

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
        quiz1.setOwner(user2);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");
        when(quizRepository.save((Quiz) any())).thenReturn(quiz1);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        Map<String, String> actualPublishQuizResult = quizService.publishQuiz(123L, 123L);
        assertEquals(1, actualPublishQuizResult.size());
        assertEquals("FAILED", actualPublishQuizResult.get("Response"));
        verify(quizRepository, atLeast(1)).findById((Long) any());
        verify(quiz).isPublished();
        verify(quiz).getOwner();
        verify(quiz).setCreationDate((Date) any());
        verify(quiz).setLastModifiedDate((Date) any());
        verify(quiz).setDeleted(anyBoolean());
        verify(quiz).setDescription((String) any());
        verify(quiz).setId(anyLong());
        verify(quiz).setMaxMarks(anyInt());
        verify(quiz).setNumberOfQuestions(anyInt());
        verify(quiz).setOwner((User) any());
        verify(quiz).setPublished(anyBoolean());
        verify(quiz).setTimeInSeconds(anyLong());
        verify(quiz).setTitle((String) any());
    }

    /**
     * Method under test: {@link QuizService#publishQuiz(Long, Long)}
     */
    @Test
    void testPublishQuiz3() {
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

        User user1 = new User();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setDeleted(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Quiz quiz = mock(Quiz.class);
        when(quiz.isPublished()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        when(quiz.getOwner()).thenReturn(user1);
        doNothing().when(quiz).setCreationDate((Date) any());
        doNothing().when(quiz).setLastModifiedDate((Date) any());
        doNothing().when(quiz).setDeleted(anyBoolean());
        doNothing().when(quiz).setDescription((String) any());
        doNothing().when(quiz).setId(anyLong());
        doNothing().when(quiz).setMaxMarks(anyInt());
        doNothing().when(quiz).setNumberOfQuestions(anyInt());
        doNothing().when(quiz).setOwner((User) any());
        doNothing().when(quiz).setPublished(anyBoolean());
        doNothing().when(quiz).setTimeInSeconds(anyLong());
        doNothing().when(quiz).setTitle((String) any());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setCreationDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setDeleted(true);
        quiz.setDescription("The characteristics of someone or something");
        quiz.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        quiz.setLastModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        quiz.setMaxMarks(3);
        quiz.setNumberOfQuestions(10);
        quiz.setOwner(user);
        quiz.setPublished(true);
        quiz.setTimeInSeconds(10L);
        quiz.setTitle("Dr");
        Optional<Quiz> ofResult = Optional.of(quiz);

        User user2 = new User();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreationDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setDeleted(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

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
        quiz1.setOwner(user2);
        quiz1.setPublished(true);
        quiz1.setTimeInSeconds(10L);
        quiz1.setTitle("Dr");
        when(quizRepository.save((Quiz) any())).thenReturn(quiz1);
        when(quizRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResponseStatusException.class, () -> quizService.publishQuiz(123L, 123L));
        verify(quizRepository, atLeast(1)).findById((Long) any());
        verify(quiz).isPublished();
        verify(quiz).getOwner();
        verify(quiz).setCreationDate((Date) any());
        verify(quiz).setLastModifiedDate((Date) any());
        verify(quiz).setDeleted(anyBoolean());
        verify(quiz).setDescription((String) any());
        verify(quiz).setId(anyLong());
        verify(quiz).setMaxMarks(anyInt());
        verify(quiz).setNumberOfQuestions(anyInt());
        verify(quiz).setOwner((User) any());
        verify(quiz).setPublished(anyBoolean());
        verify(quiz).setTimeInSeconds(anyLong());
        verify(quiz).setTitle((String) any());
    }
}

