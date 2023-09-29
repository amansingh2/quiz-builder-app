package com.quiz.quizbuilder.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quiz.quizbuilder.dto.LoginRequest;
import com.quiz.quizbuilder.entity.User;
import com.quiz.quizbuilder.repository.UserRepository;
import com.quiz.quizbuilder.security.CustomAuthenticationProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private CustomAuthenticationProvider customAuthenticationProvider;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#create(User)}
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
        when(userRepository.save((User) any())).thenReturn(user);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

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
        user1.setPassword("password");
        user1.setUsername("janedoe");
        assertSame(user, userService.create(user1));
        verify(userRepository).save((User) any());
        verify(passwordEncoder).encode((CharSequence) any());
        assertEquals("secret", user1.getPassword());
    }

    /**
     * Method under test: {@link UserService#create(User)}
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
        user.setPassword("password");
        user.setUsername("janedoe");
        when(userRepository.save((User) any())).thenReturn(user);
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

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
        user1.setPassword("password");
        user1.setUsername("janedoe");
        assertThrows(ResponseStatusException.class, () -> userService.create(user1));
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#create(User)}
     */
    @Test
    void testCreate3() {
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
        when(userRepository.save((User) any())).thenReturn(user);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        User user1 = mock(User.class);
        when(user1.getEmail()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        when(user1.getPassword()).thenReturn("password");
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
        user1.setPassword("password");
        user1.setUsername("janedoe");
        assertThrows(ResponseStatusException.class, () -> userService.create(user1));
        verify(passwordEncoder).encode((CharSequence) any());
        verify(user1).getEmail();
        verify(user1, atLeast(1)).getPassword();
        verify(user1).setCreationDate((Date) any());
        verify(user1).setDeleted(anyBoolean());
        verify(user1).setEmail((String) any());
        verify(user1).setFirstName((String) any());
        verify(user1).setId(anyLong());
        verify(user1).setLastModifiedDate((Date) any());
        verify(user1).setLastName((String) any());
        verify(user1, atLeast(1)).setPassword((String) any());
        verify(user1).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#create(User)}
     */
    @Test
    void testCreate4() {
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
        when(userRepository.save((User) any())).thenReturn(user);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        User user1 = mock(User.class);
        when(user1.getEmail()).thenReturn("");
        when(user1.getPassword()).thenReturn("password");
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
        user1.setPassword("password");
        user1.setUsername("janedoe");
        assertThrows(ResponseStatusException.class, () -> userService.create(user1));
        verify(passwordEncoder).encode((CharSequence) any());
        verify(user1).getEmail();
        verify(user1, atLeast(1)).getPassword();
        verify(user1).setCreationDate((Date) any());
        verify(user1).setDeleted(anyBoolean());
        verify(user1).setEmail((String) any());
        verify(user1).setFirstName((String) any());
        verify(user1).setId(anyLong());
        verify(user1).setLastModifiedDate((Date) any());
        verify(user1).setLastName((String) any());
        verify(user1, atLeast(1)).setPassword((String) any());
        verify(user1).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#create(User)}
     */
    @Test
    void testCreate5() {
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
        when(userRepository.save((User) any())).thenReturn(user);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        User user1 = mock(User.class);
        when(user1.getEmail()).thenReturn("jane.doe@example.org");
        when(user1.getPassword()).thenReturn("");
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
        user1.setPassword("password");
        user1.setUsername("janedoe");
        assertThrows(ResponseStatusException.class, () -> userService.create(user1));
        verify(user1).getPassword();
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
     * Method under test: {@link UserService#update(User)}
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
        user.setPassword("password");
        user.setUsername("janedoe");
        when(userRepository.save((User) any())).thenReturn(user);

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
        user1.setPassword("password");
        user1.setUsername("janedoe");
        assertSame(user, userService.update(user1));
        verify(userRepository).save((User) any());
    }

    /**
     * Method under test: {@link UserService#update(User)}
     */
    @Test
    void testUpdate2() {
        when(userRepository.save((User) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

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
        assertThrows(ResponseStatusException.class, () -> userService.update(user));
        verify(userRepository).save((User) any());
    }

    /**
     * Method under test: {@link UserService#getById(Long)}
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, userService.getById(123L));
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserService#getById(Long)}
     */
    @Test
    void testGetById2() {
        when(userRepository.findById((Long) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, () -> userService.getById(123L));
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserService#getByUsername(String)}
     */
    @Test
    void testGetByUsername() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsernameIgnoreCase((String) any())).thenReturn(ofResult);
        assertSame(user, userService.getByUsername("janedoe"));
        verify(userRepository).findByUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link UserService#getByUsername(String)}
     */
    @Test
    void testGetByUsername2() {
        when(userRepository.findByUsernameIgnoreCase((String) any())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> userService.getByUsername("janedoe"));
        verify(userRepository).findByUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link UserService#getByUsername(String)}
     */
    @Test
    void testGetByUsername3() {
        when(userRepository.findByUsernameIgnoreCase((String) any()))
                .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, () -> userService.getByUsername("janedoe"));
        verify(userRepository).findByUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link UserService#delete(Long)}
     */
    @Test
    void testDelete() {
        doNothing().when(userRepository).deleteById((Long) any());
        userService.delete(123L);
        verify(userRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link UserService#delete(Long)}
     */
    @Test
    void testDelete2() {
        doThrow(new ResponseStatusException(HttpStatus.CONTINUE)).when(userRepository).deleteById((Long) any());
        assertThrows(ResponseStatusException.class, () -> userService.delete(123L));
        verify(userRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link UserService#login(LoginRequest)}
     */
    @Test
    void testLogin() {
        when(customAuthenticationProvider.authenticateUser((LoginRequest) any())).thenReturn("Authenticate User");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("password");
        assertEquals("Authenticate User", userService.login(loginRequest));
        verify(customAuthenticationProvider).authenticateUser((LoginRequest) any());
    }

    /**
     * Method under test: {@link UserService#login(LoginRequest)}
     */
    @Test
    void testLogin2() {
        when(customAuthenticationProvider.authenticateUser((LoginRequest) any()))
                .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("password");
        assertThrows(ResponseStatusException.class, () -> userService.login(loginRequest));
        verify(customAuthenticationProvider).authenticateUser((LoginRequest) any());
    }

    /**
     * Method under test: {@link UserService#retrieveUser()}
     */
    @Test
    void testRetrieveUser() {
        assertThrows(RuntimeException.class, () -> userService.retrieveUser());
    }
}

