package com.school.school.auth.entities;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "email", "activation_token" }) })
public class UserEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @Column(name = "uid")
  private UUID uid;

  @Column(nullable = false)
  @NotEmpty
  @Size(min = 3, max = 16)
  private String username;

  @NotEmpty
  @Email
  @Column(unique = true, nullable = false)
  private String email;

  // @Pattern(regexp =
  // "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!_.%*?&])([A-Za-z0-9]){8,15}$")
  @Column
  private String password;

  @Column(name = "is_active", nullable = false, columnDefinition = "Boolean default 'false'")
  private Boolean isActive = false;

  @Column
  @Nullable
  private String resetPasswordToken;

  @Nullable
  @Column(unique = true, name = "activation_token", nullable = true)
  private Long activationToken;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String hash) {

    this.password = hash;
  }

  public String getPassword() {
    return this.password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  public Boolean getIsActive() {
    return this.isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public void setUid(UUID uid) {
    this.uid = uid;
  }
}
