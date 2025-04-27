package org.example.healthnexus1.controller;
import org.example.healthnexus1.entity.User;

import org.example.healthnexus1.repository.UserRepository;
import org.example.healthnexus1.security.JwtUtil;
import org.example.healthnexus1.service.UserService;
import org.example.healthnexus1.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userServiceImpl;

    private final UserRepository userRepository;

    public UserController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder, UserServiceImpl userServiceImpl,UserRepository userRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userServiceImpl = userServiceImpl;
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        User savedUser = userService.registerUser(user);
//        return ResponseEntity.ok(savedUser);
//    }
@PostMapping("/signup")
public ResponseEntity<?> signup(@RequestBody User user) {
    user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
    userServiceImpl.saveUser(user);
    return ResponseEntity.ok("User registered successfully");
}

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
//        try {
//            String email = loginRequest.get("email");
//            String password = loginRequest.get("password");
//            String token = userService.authenticate(email, password);
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "Login successful");
//            response.put("token", token);
//            return ResponseEntity.ok(response);
//        } catch (BadCredentialsException e) {
////            return ResponseEntity.status(401).body("Invalid credentials");
//            Map<String, String> errorResponse = new HashMap<>();
//            errorResponse.put("error", "Invalid credentials");
//            return ResponseEntity.status(401).body(errorResponse);
//        }
//    }

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    String token = jwtUtil.generateToken(user.getEmail());
    // ✅ Fetch full user entity
    User loggedInUser = userRepository.findByEmail(user.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    Map<String, String> response = new HashMap<>();
    response.put("token", token);
    response.put("userId", String.valueOf(loggedInUser.getId()));
  //  response.put("role", String.valueOf(loggedInUser.getId()));
    return ResponseEntity.ok(response);
}

//    @PostMapping("/change-password")
//    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> passwordRequest) {
//        String email = passwordRequest.get("email");
//        String oldPassword = passwordRequest.get("oldPassword");
//        String newPassword = passwordRequest.get("newPassword");
//        String confirmNewPassword = passwordRequest.get("confirmNewPassword");
//
//        if (!newPassword.equals(confirmNewPassword)) {
//            return ResponseEntity.badRequest().body("New passwords do not match");
//        }
//
//        User user = userService.getUserByEmail(email);
//        if (user == null) {
//            return ResponseEntity.status(404).body("User not found");
//        }
//
//        String token = userService.authenticate(email, oldPassword);
//        user.setPasswordHash(newPassword);
//        userService.updateUser(user.getUserId(), user);
//
//        return ResponseEntity.ok("Password updated successfully");
//    }
}
