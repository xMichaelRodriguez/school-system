package com.school.school.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "uid")
  private Long uid;

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
  private String resetPasswordToken;

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

  public void setUid(Long uid) {
    this.uid = uid;
  }
}
