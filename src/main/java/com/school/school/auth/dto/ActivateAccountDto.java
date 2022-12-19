package com.school.school.auth.dto;

import java.rmi.server.UID;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class ActivateAccountDto {

  @NotEmpty
  @NotBlank
  public UID id;

  @NotBlank
  @NotEmpty
  public UUID code;

}
