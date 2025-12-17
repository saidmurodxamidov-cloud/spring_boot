package org.example.spring_boot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring_boot.dto.request.TrainerRequest;
import org.example.spring_boot.dto.request.TrainerTrainingsRequest;
import org.example.spring_boot.dto.request.TrainerUpdateRequest;
import org.example.spring_boot.dto.response.*;
import org.example.spring_boot.entity.*;
import org.example.spring_boot.repository.TrainerRepository;
import org.example.spring_boot.repository.TrainingRepository;
import org.example.spring_boot.repository.TrainingTypeRepository;
import org.example.spring_boot.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.example.spring_boot.util.NormalizeUtil.normalize;
import static org.example.spring_boot.util.PasswordGenerator.generatePassword;
import static org.example.spring_boot.util.UsernameGenerator.generateUsername;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final BCryptPasswordEncoder bcrypt;

    @Transactional
    public AuthResponse createTrainer(TrainerRequest trainerRequest){
        Set<String> availableUsernames = userRepository.findAllUserNames();
        String username = generateUsername(trainerRequest.getFirstname(),trainerRequest.getLastname(),availableUsernames);
        char[] password = generatePassword();
        String encoded = bcrypt.encode(String.valueOf(password));
        Set<TrainingTypeEntity> specializations = trainingTypeRepository.findByTrainingTypeNameIn(trainerRequest.getSpecializations());
        if(trainerRequest.getSpecializations() != null && specializations.size() != trainerRequest.getSpecializations().size())
            throw new IllegalArgumentException();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.TRAINER);
        UserEntity user = UserEntity.builder()
                .firstName(trainerRequest.getFirstname())
                .userName(username)
                .passwordHash(encoded)
                .lastName(trainerRequest.getLastname())
                .isActive(true)
                .roles(roles)
                .build();
        TrainerEntity trainer = TrainerEntity.builder()
                .user(user)
                .specializations(specializations)
                .build();
        trainerRepository.save(trainer);
        return new AuthResponse(username,new String(password));
    }
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('TRAINER')")
    public TrainerProfileResponse getTrainerProfile(String username){
        log.debug("getting trainer with username : {} ", username);
        TrainerEntity trainerEntity = trainerRepository.findByUserUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
        UserEntity user  = trainerEntity.getUser();

        List<TraineeResponse> trainees =  trainerEntity.getTrainees().stream()
                .map(trainee -> TraineeResponse.builder()
                        .lastname(trainee.getUser().getLastName())
                        .firstname(trainee.getUser().getFirstName())
                        .username(trainee.getUser().getUserName())
                        .build()).toList();

        List<TrainingTypeResponse> specializations = trainerEntity.getSpecializations().stream().map(spec ->
                new TrainingTypeResponse(spec.getId(),spec.getTrainingTypeName())).toList();

        return TrainerProfileResponse.builder()
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .specialization(specializations)
                .isActive(user.isActive())
                .trainees(trainees)
                .build();
    }

    @Transactional
    @PreAuthorize("hasRole('TRAINER')")
    public TrainerUpdateResponse updateTrainer(TrainerUpdateRequest request){
        log.debug("updating trainer {}",request.getUsername());
        TrainerEntity trainer = trainerRepository.findByUserUserName(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user does not exist + " + request.getUsername()));

        trainer.getUser().setFirstName(request.getFirstname());
        trainer.getUser().setLastName(request.getLastname());
        trainer.getUser().setActive(request.getIsActive());

        List<TraineeResponse> trainees = trainer.getTrainees().stream()
                .map(trainee -> TraineeResponse.builder()
                .firstname(trainee.getUser().getFirstName())
                .lastname(trainee.getUser().getLastName())
                .username(trainee.getUser().getUserName())
                .build()).toList();

        List<TrainingTypeResponse> specs = trainer.getSpecializations().stream()
                .map(spec ->
                        new TrainingTypeResponse(spec.getId(),spec.getTrainingTypeName()))
                .toList();

        log.info("updated successfully the trainer {}",request.getUsername());

        return TrainerUpdateResponse.builder()
                .firstname(trainer.getUser().getFirstName())
                .isActive(trainer.getUser().isActive())
                .lastname(trainer.getUser().getLastName())
                .specialization(specs)
                .trainees(trainees)
                .username(request.getUsername())
                .build();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('TRAINER') or hasRole('TRAINER')")
    public List<TrainerResponse> getActiveTrainersNotAssignedToTrainee(String username){
        log.debug("getting active trainer not assigned to trainee {}", username);
        List<TrainerEntity> trainers = trainerRepository.findTrainersNotAssignedToTrainee(username);
        return trainers.stream().map(trainer -> {
            List<TrainingTypeResponse> specs = trainer.getSpecializations().stream()
                    .map(spec -> new TrainingTypeResponse(spec.getId(),spec.getTrainingTypeName())).toList();
           return TrainerResponse.builder()
                    .firstname(trainer.getUser().getFirstName())
                    .lastname(trainer.getUser().getLastName())
                    .username(trainer.getUser().getUserName())
                    .trainingTypeResponse(specs)
                    .build();
        }).toList();
    }
    @Transactional(readOnly = true)
    public List<TrainingResponse> getTrainerTrainings(TrainerTrainingsRequest request){
        log.debug("getting trainer {} training list by creteria", request.getTraineeName());
        if(!trainerRepository.existsByUserUserName(request.getUsername()))
            throw new UsernameNotFoundException("user not found " + request.getUsername());
        String trainingType = normalize(request.getTrainingType());
        String traineeName = normalize(request.getTraineeName());

        List<TrainingEntity> trainings = trainingRepository.findTrainerTrainingsByCriteria(request.getUsername(),request.getFrom(),request.getTo(),traineeName);
        return trainings.stream().map(training ->
                TrainingResponse.builder()
                        .traineeUsername(request.getUsername())
                        .trainingType(training.getTrainingType().getTrainingTypeName())
                        .trainingDate(training.getDate())
                        .durationInMinutes(training.getTrainingDuration().toMinutesPart())
                        .trainingName(training.getTrainingName())
                        .build()).toList();
    }
}
