package com.ism.services.impl;

import java.util.List;
import com.ism.entities.User;
import com.ism.enums.Role;
import com.ism.repository.Repository;
import com.ism.services.UserService;

public class UserServiceImpl implements UserService<User> {
    private final Repository<User> userRepository;

    public UserServiceImpl(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.insert(user);
    }

    @Override
    public List<User> show() {
        return userRepository.selectAll();
    }

    public void createUser(String login, String password, Role role) {
        // Create a new User object
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setRole(role);

        // Call the create method to save the user
        create(newUser);
    }
}
