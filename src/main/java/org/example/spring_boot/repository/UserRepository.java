package org.example.spring_boot.repository;

import org.example.spring_boot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUserName(String username);

    boolean existsByUserName(String username);

    @Query("SELECT u.userName FROM UserEntity u")
    Set<String> findAllUserNames();

}
