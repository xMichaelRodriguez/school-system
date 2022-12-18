package com.school.school.auth.services;

public interface EncoderService {

  /**
   * Encode password
   * 
   * @param password
   * @return -> password hashed
   */
  String encodePassword(String password);

  /**
   * verify if the password and hash are the same
   * 
   * @param password -> plainText of user password
   * @param hash     -> database hash password
   * @return Boolean
   */
  Boolean checkPassword(String password, String hash);

}
