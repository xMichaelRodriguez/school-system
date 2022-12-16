package com.school.school.mapper;

import org.springframework.stereotype.Component;

import com.school.school.dto.CreateUserDto;
import com.school.school.entities.UserEntity;
import com.school.school.services.EncoderServiceImp;

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

    return userEntity;
  }

}
