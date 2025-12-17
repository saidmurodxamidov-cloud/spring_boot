package org.example.spring_boot.repository;

import org.example.spring_boot.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity,Long> {
    Optional<TrainerEntity> findByUserUserName(String username);

    @Query("SELECT t FROM TrainerEntity t WHERE t NOT IN " +
            "(SELECT tr FROM TraineeEntity tn JOIN tn.trainers tr WHERE tn.user.userName = :traineeUsername AND t.user.isActive = true)")
    List<TrainerEntity> findTrainersNotAssignedToTrainee(@Param("traineeUsername") String traineeUsername);

    List<TrainerEntity> findByUserIsActive(boolean isActive);

    boolean existsByUserUserName(String username);

    Set<TrainerEntity> findByUserUserNameIn(List<String> usernames);


}
