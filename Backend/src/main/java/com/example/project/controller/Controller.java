package com.example.project.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Login;
import com.example.project.model.RegistrationRequest;
import com.example.project.model.Response;
import com.example.project.model.TextInput;
import com.example.project.model.User;
import com.example.project.repo.UserRepository;
import com.example.project.service.implementation.TextInputServiceImplementation;

import lombok.RequiredArgsConstructor;

//@CrossOrigin(origins = {"http://localhost:4200/**"}, maxAge = 3600, allowCredentials = "false")
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class Controller {

    @Autowired
    public TextInputServiceImplementation TextInputService;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody Login login) {

        boolean isAuthenticated = authenticateUser(login.getUsername(), login.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(Instant.now())
                            .message("Login successful")
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(
                            Response.builder()
                                    .timeStamp(Instant.now())
                                    .message("Login failed")
                                    .build());
        }
    }

    public boolean authenticateUser(String username, String password) {

        ArrayList<User> users = UserRepository.loadUsers();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username) && BCrypt.checkpw(password, user.getPassword())) {
                return true;
            }
        }

        return false;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        // Validate registration data (e.g., check if the username is unique)
        if (!UserRepository.isUsernameUnique(registrationRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Response.builder()
                            .timeStamp(Instant.now())
                            .message("Username is already in use")
                            .build());
        }

        // Save the user to the database
        UserRepository.addUser(registrationRequest.getUsername(), registrationRequest.getPassword(), false);

        // Return a success response
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(Instant.now())
                        .message("Registration successful")
                        .build());
    }

    // @GetMapping("/list")
    // public ResponseEntity<Response> getTextInputs() {
    // return ResponseEntity.ok(
    // Response.builder()
    // .timeStamp(Instant.now())
    // .data(Map.of("textInputs", TextInputService.loadTextInputs()))
    // .message("textInputs retrieved")
    // .build()
    //
    //
    // );
    // }

    @GetMapping("/list")
    public ResponseEntity<TextInput[]> getTextInputs() {
        TextInput[] textInputs = TextInputService.loadTextInputs();
        return ResponseEntity.ok()
                .body(textInputs);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getTextInput(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(Instant.now())
                        .data(Map.of("textInput", TextInputService.loadTextInputsByUser(id)))
                        .message("textInput retrieved")
                        .build()

        );
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addTextInput(@RequestBody TextInput textInput) {
        String text = textInput.getText();
        String username = textInput.getUsername();
        System.out.println("Request recieved for /add, username: " + username + ", text: " + text);

        long time = System.currentTimeMillis();

        TextInputService.addTextInput(text, time, username);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(Instant.now())
                        .message("textInput added")
                        .build());
    }

    // For admin users to delete a text input.
    @DeleteMapping("/delete/{time}")
    public ResponseEntity<Response> deleteTextInput(Long time) {
        TextInputService.deleteTextInput(time);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(Instant.now())
                        .message("textInput deleted")
                        .build());
    }

    // Filter text inputs based on content.
    @GetMapping("/filter")
    public ResponseEntity<Response> filterTextInputs(@RequestParam String content) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(Instant.now())
                        .data(Map.of("filteredTextInputs", TextInputService.loadTextInputsByContent(content)))
                        .message("filtered textInputs retrieved")
                        .build());
    }

    // Filter text inputs based on time.
    @GetMapping("/time")
    public ResponseEntity<Response> timeTextInputs(@RequestParam long startTime, long endTime) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(Instant.now())
                        .data(Map.of("timeTextInputs", TextInputService.loadTextInputsByTime(startTime, endTime)))
                        .message("timed textInputs retrieved")
                        .build());
    }
}
