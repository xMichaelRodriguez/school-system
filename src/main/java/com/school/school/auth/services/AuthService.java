package com.school.school.auth.services;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.UUID;

import org.aspectj.apache.bcel.classfile.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import com.school.school.auth.dto.CreateUserDto;
import com.school.school.auth.dto.LoginUserDto;
import com.school.school.auth.entities.UserEntity;
import com.school.school.errors.MyCustomExceptions;
import com.school.school.auth.mapper.UserDtoToUser;
import com.school.school.auth.repositories.UserRepository;

@Service
public class AuthService {
  private final static Logger log = LoggerFactory.getLogger(AuthService.class);
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

  public UserEntity createUser(CreateUserDto user) throws MyCustomExceptions {
    UserEntity userEntity = userDtoToUser.map(user);
    try {
      UserEntity userSaved = this.userRepository.save(userEntity);
      return userSaved;
    } catch (Exception e) {
      final String causeError = e.getMessage().toLowerCase();

      if (causeError.indexOf("constraint") > 0) {
        throw new MyCustomExceptions("There is already a mailing with " + user.email,
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase());
      }

      throw e;
    }

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

  public HttpStatus activateAccount(String code, String uid) throws MyCustomExceptions {

    UUID id = UUID.fromString(uid);
    UUID codeParsed = UUID.fromString(code);
    UserEntity user = this.userRepository.findByUidAndActivationTokenAndIsActive(id, codeParsed, false);
    log.info("PERROOOOOOOOOOOOOOOOOOOOOOO " + user.getIsActive());
    // if (user == null) {
    // throw new MyCustomExceptions("This action can not be done",
    // HttpStatus.UNPROCESSABLE_ENTITY.value(),
    // HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
    // }

    // user.setActivationToken(null);
    // user.setIsActive(true);

    // this.userRepository.save(user);

    return HttpStatus.OK;
  }

}
