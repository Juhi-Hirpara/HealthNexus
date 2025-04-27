package org.example.healthnexus1.service.impl;

import org.example.healthnexus1.entity.User;
import org.example.healthnexus1.repository.UserRepository;
import org.example.healthnexus1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;


//    @Override
//    public User registerUser(User user) {
//        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
//        return userRepository.save(user);
//    }
//
////    @Override
////    public String authenticate(String email, String password) {
////        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
////        User user = userRepository.findByEmail(email)
////                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
////        return jwtUtil.generateToken(user);
////    }
//
//    @Override
//    public String authenticate(String email, String password) {
//        try {
//            // Authenticate the user using the authentication manager
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(email, password)
//            );
//
//            // Retrieve the authenticated user details
//            User user = userRepository.findByEmail(email)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//            // Generate JWT token
//            return jwtUtil.generateToken(user);
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException("Invalid email or password");
//        } catch (Exception e) {
//            throw new RuntimeException("Authentication failed", e);
//        }
//    }
//
//
//    @Override
//    public User getUserById(Long id) {
//        Optional<User> user = userRepository.findById(id);
//        return user.orElse(null);
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public User updateUser(Long id, User user) {
//        if (userRepository.existsById(id)) {
//            user.setUserId(id);
//            user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
//            return userRepository.save(user);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    @Override
//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email).orElse(null);
//    }
//}
}