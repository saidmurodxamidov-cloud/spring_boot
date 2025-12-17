package org.example.persistence.repository;

import org.example.persistence.entity.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<TraineeEntity,Long> {
    Optional<TraineeEntity> findByUserUserName(String username);

    void deleteByUserUserName(String username);

    List<TraineeEntity> findByUserIsActive(boolean isActive);

    boolean existsByUserUserName(String username);
}
