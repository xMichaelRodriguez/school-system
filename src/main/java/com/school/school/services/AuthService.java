package com.school.school.services;

import java.util.ArrayList;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.school.entities.UserEntity;
import com.school.school.repositories.UserRepository;

@Service
public class AuthService {
  @Autowired
  UserRepository userRepository;

  public ArrayList<UserEntity> findUsers() {
    return (ArrayList<UserEntity>) this.userRepository.findAll();
  }

  public UserEntity createUser(UserEntity user) {
    return userRepository.save(user);
  }

}
