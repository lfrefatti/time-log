package br.com.lfr.timelog.service;

import br.com.lfr.timelog.domain.User;

import java.util.Optional;

public interface UserService {

    User findUserById(String id);

    Optional<User> findUserByLogin(String login);

    User insertUser(User userToInsert);

    User updateUser(String id, User userToUpdate);
}
