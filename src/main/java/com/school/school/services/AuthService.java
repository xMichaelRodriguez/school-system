package com.school.school.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.school.school.dto.CreateUserDto;
import com.school.school.entities.UserEntity;
import com.school.school.mapper.UserDtoToUser;
import com.school.school.repositories.UserRepository;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final UserDtoToUser userDtoToUser;

  public AuthService(UserRepository userRepository, UserDtoToUser userDtoToUser) {
    this.userRepository = userRepository;
    this.userDtoToUser = userDtoToUser;
  }

  public ArrayList<UserEntity> findUsers() {
    return (ArrayList<UserEntity>) this.userRepository.findAll();
  }

  public UserEntity createUser(CreateUserDto user) {
    UserEntity userEntity = userDtoToUser.map(user);

    return this.userRepository.save(userEntity);
  }

}
