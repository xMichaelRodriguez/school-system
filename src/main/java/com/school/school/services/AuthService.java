package com.school.school.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.school.dto.CreateUserDto;
import com.school.school.entities.UserEntity;
import com.school.school.repositories.UserRepository;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EncoderServiceImp encoderServiceImp;

  public ArrayList<UserEntity> findUsers() {
    return (ArrayList<UserEntity>) this.userRepository.findAll();
  }

  public <T> T createUser(CreateUserDto user) {
    try {

      String hash = this.encoderServiceImp.encodePassword(user.password);
      UserEntity userEntity = new UserEntity();
      userEntity.setPassword(hash);
      userEntity.setEmail(user.email);
      userEntity.setUsername(user.username);
      return (T) userRepository.save(userEntity);
    } catch (Exception e) {
      System.out.println(e);
      return (T) e.getMessage();
    }
  }

}
