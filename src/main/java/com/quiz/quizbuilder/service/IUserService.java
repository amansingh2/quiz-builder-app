package com.quiz.quizbuilder.service;

import com.quiz.quizbuilder.dto.LoginRequest;
import com.quiz.quizbuilder.entity.User;

public interface IUserService {

    User create(User user);
    User update(User user);
    User getById(Long id);
    User getByUsername(String username);
    void delete(Long id);

    String login(LoginRequest request);

    User retrieveUser();

}
