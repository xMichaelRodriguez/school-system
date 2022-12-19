package com.school.school.auth.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class ActivateAccountDto {

  @NotBlank
  public String uuid;
  @NotBlank
  public String code;

}
