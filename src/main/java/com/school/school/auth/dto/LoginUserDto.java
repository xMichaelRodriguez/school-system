package com.school.school.auth.dto;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class LoginUserDto {
  @NotEmpty
  @NotBlank
  @Email
  public String email;

  @Nullable
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!_.%*?&])([A-Za-z0-9]){8,15}$")
  public String password;

}
