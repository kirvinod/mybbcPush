package uk.co.bbc.mybbc.services;

import uk.co.bbc.mybbc.entities.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findUserByUsername(String username);

    void save(User user);
}
