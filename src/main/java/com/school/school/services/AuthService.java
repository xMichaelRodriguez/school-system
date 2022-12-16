package com.school.school.services;

import java.util.ArrayList;

// import org.slf4j.Logger;
//  import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.school.school.dto.CreateUserDto;
import com.school.school.dto.LoginUserDto;
import com.school.school.entities.UserEntity;
import com.school.school.errors.MyCustomExceptions;
import com.school.school.mapper.UserDtoToUser;
import com.school.school.repositories.UserRepository;

@Service
public class AuthService {
  // private final static Logger log = LoggerFactory.getLogger(AuthService.class);
  private final UserRepository userRepository;
  private final UserDtoToUser userDtoToUser;
  private final EncoderServiceImp encoderServiceImp;

  public AuthService(UserRepository userRepository, UserDtoToUser userDtoToUser, EncoderServiceImp encoderServiceImp) {
    this.userRepository = userRepository;
    this.userDtoToUser = userDtoToUser;
    this.encoderServiceImp = encoderServiceImp;
  }

  public ArrayList<UserEntity> findUsers() {
    return (ArrayList<UserEntity>) this.userRepository.findAll();
  }

  public UserEntity createUser(CreateUserDto user) {
    UserEntity userEntity = userDtoToUser.map(user);

    return this.userRepository.save(userEntity);
  }

  public LoginUserDto loginUser(LoginUserDto user) throws MyCustomExceptions {

    UserEntity userEntity = this.findByEmail(user.email);

    Boolean checkPassword = this.encoderServiceImp.checkPassword(user.password,
        userEntity.getPassword());

    if (!checkPassword) {
      throw new MyCustomExceptions("Check your credentials", HttpStatus.UNAUTHORIZED.value(),
          HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
    if (!userEntity.getIsActive()) {
      throw new MyCustomExceptions("Please active Your Account", HttpStatus.UNAUTHORIZED.value(),
          HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    return user;
  }

  private UserEntity findByEmail(String email) {
    UserEntity userEntity = this.userRepository.findByEmail(email);

    return userEntity;
  }

}
