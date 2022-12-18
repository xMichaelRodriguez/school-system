package com.school.school.auth.controllers;

import java.util.ArrayList;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.school.auth.dto.CreateUserDto;
import com.school.school.auth.dto.LoginUserDto;
import com.school.school.auth.entities.UserEntity;
import com.school.school.errors.MyCustomExceptions;
import com.school.school.auth.services.AuthService;

@RestController
@RequestMapping("/auth/")
public class AuthController {
  // private final static Logger log =
  // LoggerFactory.getLogger(AuthController.class);
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @GetMapping("/users")
  public ArrayList<UserEntity> getUsers() {
    return authService.findUsers();
  }

  @PostMapping("/register")
  public UserEntity createUser(@RequestBody CreateUserDto user) throws MyCustomExceptions {

    return this.authService.createUser(user);
  }

  @PostMapping("/login")
  public LoginUserDto login(@RequestBody LoginUserDto loginUserDto) throws MyCustomExceptions {
    return this.authService.loginUser(loginUserDto);
  }

}
