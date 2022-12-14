package com.school.school.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.school.entities.UserEntity;
import com.school.school.services.AuthService;

@RestController()
@RequestMapping("/auth/")
public class Auth {

  @Autowired
  AuthService authService;

  @GetMapping("/users")
  public ArrayList<UserEntity> getUsers() {
    return authService.findUsers();
  }

  @PostMapping("/register")
  public UserEntity createUser(@RequestBody UserEntity user) {

    return authService.createUser(user);
  }

}
