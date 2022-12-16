package com.school.school.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.school.dto.CreateUserDto;
import com.school.school.dto.LoginUserDto;
import com.school.school.entities.UserEntity;
import com.school.school.services.AuthService;

@RestController
@RequestMapping("/auth/")
public class AuthController {
  private final static Logger log = LoggerFactory.getLogger(AuthController.class);
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @GetMapping("/users")
  public ArrayList<UserEntity> getUsers() {
    log.warn("TEST");
    return authService.findUsers();
  }

  @PostMapping("/register")
  public UserEntity createUser(@RequestBody CreateUserDto user) {

    return this.authService.createUser(user);
  }

  @PostMapping("/login")
  public UserEntity login(@RequestBody LoginUserDto loginUserDto) {
    return this.authService.loginUser(loginUserDto);
  }

}
