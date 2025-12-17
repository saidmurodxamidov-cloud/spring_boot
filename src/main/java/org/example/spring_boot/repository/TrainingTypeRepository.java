package org.example.spring_boot.repository;

import org.example.spring_boot.entity.TrainingTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingTypeEntity,Long> {
    Optional<TrainingTypeEntity> findByTrainingTypeName(String trainingTypeName);

    boolean existsByTrainingTypeName(String trainingTypeName);

    Set<TrainingTypeEntity> findByTrainingTypeNameIn(List<String> trainingTypes);
}
