package com.example.User_Note.controller;

import com.example.User_Note.dto.LoginRequest;
import com.example.User_Note.exception.EmptyFieldException;
import com.example.User_Note.exception.InvalidCredentialsException;
import com.example.User_Note.exception.UsernameExistsException;
import com.example.User_Note.model.Users;
import com.example.User_Note.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user) throws UsernameExistsException, EmptyFieldException
    {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("created the user");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest user) throws InvalidCredentialsException
    {
        userService.validateUser(user.getUsername(),user.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body("login successful");
    }
}
