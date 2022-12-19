package com.school.school.auth.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.school.auth.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  public UserEntity findByEmail(String email);

  public UserEntity findByUid(UUID id);

  public UserEntity findByUidAndActivationTokenAndIsActive(UUID id, UUID code, Boolean isActive);

}
