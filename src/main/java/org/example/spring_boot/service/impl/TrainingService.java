package org.example.spring_boot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring_boot.dto.request.TrainingAddRequest;
import org.example.spring_boot.entity.TraineeEntity;
import org.example.spring_boot.entity.TrainerEntity;
import org.example.spring_boot.entity.TrainingEntity;
import org.example.spring_boot.entity.TrainingTypeEntity;
import org.example.spring_boot.repository.TraineeRepository;
import org.example.spring_boot.repository.TrainerRepository;
import org.example.spring_boot.repository.TrainingRepository;
import org.example.spring_boot.repository.TrainingTypeRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    @Transactional
    public void addTraining(TrainingAddRequest request){
        TraineeEntity trainee = traineeRepository.findByUserUserName(request.getTraineeUsername())
                .orElseThrow(() -> new UsernameNotFoundException("trainee does not exist " + request.getTraineeUsername()));
        TrainerEntity trainer = trainerRepository.findByUserUserName(request.getTrainerUsername())
                .orElseThrow(() -> new UsernameNotFoundException("trainer does not exist " + request.getTraineeUsername()));
        TrainingTypeEntity trainingType = trainingTypeRepository.findByTrainingTypeName(request.getTrainingType())
                .orElseThrow(() -> new IllegalArgumentException("training does not exist " + request.getTrainingType()));
        trainer.getTrainees().add(trainee);
        trainee.getTrainers().add(trainer);
        TrainingEntity training = TrainingEntity.builder()
                .trainingDuration(Duration.ofMinutes(request.getTrainingDurationInMinutes()))
                .date(request.getTrainingDate())
                .trainingName(request.getTrainingName())
                .trainingType(trainingType)
                .trainee(trainee)
                .trainer(trainer)
                .build();
        trainingRepository.save(training);
        trainer.getTrainings().add(training);
        trainee.getTrainings().add(training);
        log.info("successfully created training with name {}", training.getTrainingName());
    }

}
