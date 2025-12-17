package org.example.spring_boot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_boot.dto.request.TrainerRequest;
import org.example.spring_boot.dto.request.TrainerTrainingsRequest;
import org.example.spring_boot.dto.request.TrainerUpdateRequest;
import org.example.spring_boot.dto.response.*;
import org.example.spring_boot.service.impl.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    @PostMapping("/register")
    public ResponseEntity<?> createTrainer(@Validated @RequestBody TrainerRequest trainerRequest){
        AuthResponse authResponse = trainerService.createTrainer(trainerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }
    @GetMapping("/{username}")
    public ResponseEntity<TrainerProfileResponse> getTrainerProfile(@PathVariable String username){
        TrainerProfileResponse trainer = trainerService.getTrainerProfile(username);
        return ResponseEntity.status(HttpStatus.OK).body(trainer);
    }

    @PutMapping
    public ResponseEntity<?> updateTrainer(@RequestBody @Valid TrainerUpdateRequest request){
        TrainerUpdateResponse trainerUpdateResponse = trainerService.updateTrainer(request);
        return ResponseEntity.status(HttpStatus.OK).body(trainerUpdateResponse);
    }
    @GetMapping("/{username}/available-trainers")
    public ResponseEntity<?> getActiveTrainersNotAssignedToTrainee(@PathVariable String username){
        List<TrainerResponse> availableTrainers =trainerService.getActiveTrainersNotAssignedToTrainee(username);
        return ResponseEntity.status(HttpStatus.OK).body(availableTrainers);
    }
    @GetMapping("/trainings/byCriteria")
    public ResponseEntity<?> getTrainerTrainingsByCriteria(@RequestBody TrainerTrainingsRequest request){
        List<TrainingResponse> trainings = trainerService.getTrainerTrainings(request);
        return ResponseEntity.status(HttpStatus.OK).body(trainings);
    }

}
