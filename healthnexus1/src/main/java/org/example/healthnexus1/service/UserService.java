package org.example.healthnexus1.service;

import org.example.healthnexus1.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}

