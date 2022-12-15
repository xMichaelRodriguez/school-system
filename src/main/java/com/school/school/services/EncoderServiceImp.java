package com.school.school.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncoderServiceImp implements EncoderService {

  private String salt = BCrypt.gensalt();

  @Override
  public String encodePassword(String password) {
    String hash = BCrypt.hashpw(password, salt);
    return hash;
  }

  @Override
  public Boolean checkPassword(String password, String hash) {
    Boolean isValid = BCrypt.checkpw(password, hash);
    return isValid;
  }

}
