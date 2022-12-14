package com.school.school.auth.mapper;

import org.springframework.stereotype.Component;

import com.school.school.auth.dto.CreateUserDto;
import com.school.school.auth.entities.UserEntity;
import com.school.school.auth.services.EncoderServiceImp;

@Component
public class UserDtoToUser implements IMapper<CreateUserDto, UserEntity> {
  // private final Logger logger = LoggerFactory.getLogger(UserDtoToUser.class);
  private final EncoderServiceImp encoderServiceImp;

  public UserDtoToUser(EncoderServiceImp encoderServiceImp) {
    this.encoderServiceImp = encoderServiceImp;
  }

  @Override
  public UserEntity map(CreateUserDto in) {
    String hash = this.encoderServiceImp.encodePassword(in.password);
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(in.username);
    userEntity.setEmail(in.email);
    userEntity.setPassword(hash);
    userEntity.setIsActive(false);
    userEntity.setActivationToken(java.util.UUID.randomUUID());

    return userEntity;
  }

}
