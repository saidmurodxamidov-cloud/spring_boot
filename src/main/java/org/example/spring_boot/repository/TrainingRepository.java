package org.example.spring_boot.repository;

import org.example.spring_boot.entity.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {

    @Query("""
           SELECT tr
           FROM TrainingEntity tr
           WHERE tr.trainee.user.userName = :username
             AND (:fromDate IS NULL OR tr.date >= :fromDate)
             AND (:toDate IS NULL OR tr.date <= :toDate)
             AND (
                  :trainerName IS NULL OR
                  tr.trainer.user.firstName LIKE %:trainerName%
                  OR tr.trainer.user.lastName LIKE %:trainerName%
             )
             AND (:trainingTypeName IS NULL OR tr.trainingType.trainingTypeName = :trainingTypeName)
           """)
    List<TrainingEntity> findTraineeTrainingsByCriteria(
            @Param("username") String username,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("trainerName") String trainerName,
            @Param("trainingTypeName") String trainingTypeName
    );


    @Query("""
           SELECT tr
           FROM TrainingEntity tr
           WHERE tr.trainer.user.userName = :username
             AND (:fromDate IS NULL OR tr.date >= :fromDate)
             AND (:toDate IS NULL OR tr.date <= :toDate)
             AND (
                  :traineeName IS NULL OR
                  tr.trainee.user.firstName LIKE %:traineeName%
                  OR tr.trainee.user.lastName LIKE %:traineeName%
             )
           """)
    List<TrainingEntity> findTrainerTrainingsByCriteria(
            @Param("username") String username,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("traineeName") String traineeName
    );


    List<TrainingEntity> findByTraineeUserUserName(String username);

    List<TrainingEntity> findByTrainerUserUserName(String username);
}
