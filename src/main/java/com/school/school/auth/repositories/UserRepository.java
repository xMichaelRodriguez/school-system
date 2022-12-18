package com.school.school.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.school.auth.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  public UserEntity findByEmail(String email);

}
