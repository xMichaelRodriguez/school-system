package com.school.school.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.school.school.dto.CreateUserDto;
import com.school.school.dto.LoginUserDto;
import com.school.school.entities.UserEntity;
import com.school.school.mapper.UserDtoToUser;
import com.school.school.repositories.UserRepository;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final UserDtoToUser userDtoToUser;
  private final EncoderService encoderService;

  public AuthService(UserRepository userRepository, UserDtoToUser userDtoToUser, EncoderService encoderService) {
    this.userRepository = userRepository;
    this.userDtoToUser = userDtoToUser;
    this.encoderService = encoderService;
  }

  public ArrayList<UserEntity> findUsers() {
    return (ArrayList<UserEntity>) this.userRepository.findAll();
  }

  public UserEntity createUser(CreateUserDto user) {
    UserEntity userEntity = userDtoToUser.map(user);

    return this.userRepository.save(userEntity);
  }

  public UserEntity loginUser(LoginUserDto user) {

    UserEntity userEntity = this.findByEmail(user.email);

    Boolean checkPassword = this.encoderService.checkPassword(user.password, userEntity.getPassword());

    if (checkPassword) {
      throw new ResponseStatusException(404, "Check Your Credentials", null);
    }
    return userEntity;

  }

  private UserEntity findByEmail(String email) {
    UserEntity userEntity = this.userRepository.findByEmailEntity(email);

    return userEntity;
  }

}
