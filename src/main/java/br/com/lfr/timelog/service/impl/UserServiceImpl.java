package br.com.lfr.timelog.service.impl;

import br.com.lfr.timelog.exceptions.DuplicatedLoginException;
import br.com.lfr.timelog.exceptions.UserNotFoundException;
import br.com.lfr.timelog.repositories.UserRepository;
import br.com.lfr.timelog.domain.User;
import br.com.lfr.timelog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent())
            return user.get();

        throw new UserNotFoundException(id);
    }

    public Optional<User> findUserByLogin(String login){
        User exampleUser = new User();
        exampleUser.setLogin(login);

        return userRepository.findOne(Example.of(exampleUser));
    }

    public User insertUser(User userToInsert) {
        userToInsert.validate();
        validateLogin(userToInsert.getLogin());
        userToInsert.encodePassword();
        return userRepository.insert(userToInsert);
    }

    public User updateUser(String id, User userToUpdate) {
        userToUpdate.validate();
        User user = findUserById(id);

        if (!user.getLogin().equals(userToUpdate.getLogin()))
            validateLogin(userToUpdate.getLogin());

        userToUpdate.setId(id);
        userToUpdate.encodePassword();
        return userRepository.save(userToUpdate);
    }

    protected void validateLogin(String login){
        if (findUserByLogin(login).isPresent())
            throw new DuplicatedLoginException(login);
    }

}
