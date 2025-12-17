package org.example.persistence.repository;

import org.example.persistence.entity.TrainingTypeEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingTypeEntity,Long> {
    Optional<TrainingTypeEntity> findByTrainingTypeName(String trainingTypeName);

    boolean existsByTrainingTypeName(String trainingTypeName);

    Set<TrainingTypeEntity> findByTrainingTypeNameIn(List<String> trainingTypes);
}
