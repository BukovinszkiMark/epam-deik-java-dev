package com.epam.training.ticketservice.services.impl;

import com.epam.training.ticketservice.core.User.User;
import com.epam.training.ticketservice.services.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private User currentUser = null;
    private List<User> userList;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @PostConstruct
    public void init() {
        userList = new ArrayList<>();
        userList.add(new User("admin", "admin", User.Role.ADMIN));
    }

    @Override
    public String signInPrivileged(String username, String password) {
        Optional<User> optional = getByName(username);
        if (optional.isPresent()) {
            User user = optional.get();
            if (user.getPassword().equals(password)) {
                currentUser = user;
                return null;
            }
        }
        return "Login failed due to incorrect credentials";
    }

    @Override
    public void signOut() {
        currentUser = null;
    }

    @Override
    public String describeAccount() {
        if (currentUser == null) {
            return "You are not signed in";
        }
        if (currentUser.getRole().equals(User.Role.ADMIN)) {
            return "Signed in with privileged account '" + currentUser.getUsername() + "'";
        }
        return "USER role handling is not yet implemented";
    }

    public Optional<User> getByName(String username) {
        return userList.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(currentUser);
    }
}

