package com.school.school.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "uid")
  private Long uid;

  @Column(nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

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
