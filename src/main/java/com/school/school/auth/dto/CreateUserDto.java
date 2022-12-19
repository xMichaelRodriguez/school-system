package com.school.school.auth.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class CreateUserDto extends LoginUserDto {
  @NotEmpty
  @NotBlank
  @Length(min = 3, max = 16)
  public String username;

}
