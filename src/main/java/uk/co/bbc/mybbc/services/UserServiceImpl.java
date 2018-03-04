package uk.co.bbc.mybbc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.bbc.mybbc.entities.User;
import uk.co.bbc.mybbc.exceptions.UserAlreadyExists;
import uk.co.bbc.mybbc.repositories.UserRepository;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User register(User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) throw new UserAlreadyExists("User with this username already exists.");

        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save (user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


}
