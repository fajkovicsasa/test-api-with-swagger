package com.way2automation.testapi.service;

import com.way2automation.testapi.exception.UserNotFoundException;
import com.way2automation.testapi.model.User;
import com.way2automation.testapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> userList = this.userRepository.findAll();

        return userList;
    }

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = this.userRepository.findById(id);
        User user = null;

        if (userOptional.isPresent()) {
            return user = userOptional.get();
        }

        throw new UserNotFoundException(id);
    }

    public void createUser(User user) {
        this.userRepository.save(user);

    }

    public void updateUser(User user) {
        this.userRepository.save(user);

    }

    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    public void patchUser(User user) {
        User populateUsed = getUserById(user.getId());

        if (user.getEmail() == null || user.getEmail().trim().equals("")) {
            user.setEmail(populateUsed.getEmail());
        }

        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) {
            user.setFirstName(populateUsed.getFirstName());
        }

        if (user.getLastName() == null || user.getLastName().trim().equals("")) {
            user.setLastName(populateUsed.getLastName());
        }

        this.userRepository.save(user);
    }

}
