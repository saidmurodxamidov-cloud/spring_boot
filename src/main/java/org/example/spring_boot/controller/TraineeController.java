package org.example.spring_boot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_boot.dto.request.TraineeRegistrationRequest;
import org.example.spring_boot.dto.request.TraineeTrainingRequest;
import org.example.spring_boot.dto.request.TraineeUpdateRequest;
import org.example.spring_boot.dto.response.*;
import org.example.spring_boot.service.impl.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trainees")
public class TraineeController {

    private final TraineeService traineeService;

    @PostMapping("/register")
    public ResponseEntity<?> createTrainee(@Validated @RequestBody TraineeRegistrationRequest traineeRequest){
        AuthResponse authResponse = traineeService.createTrainee(traineeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }
    @GetMapping("/{username}")
    public ResponseEntity<?> getTrainer(@PathVariable String username){
        TraineeProfileResponse traineeProfileResponse =  traineeService.getTrainee(username);
        return ResponseEntity.status(HttpStatus.OK).body(traineeProfileResponse);
    }

    @PutMapping
    public TraineeUpdateResponse updateTrainee(@RequestBody @Valid TraineeUpdateRequest traineeUpdateRequest){
        return traineeService.updateTrainee(traineeUpdateRequest);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteTrainee(@PathVariable String username){
        traineeService.deleteTrainee(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("{username}/trainers")
    public ResponseEntity<?> updateTraineeTrainersList(@PathVariable String username, List<String> trainerUsernames){
        List<TrainerResponse> trainers = traineeService.updateTraineesTrainerList(username,trainerUsernames);
        return ResponseEntity.status(HttpStatus.OK).body(trainers);
    }
    @GetMapping
    public ResponseEntity<?> getTraineeTrainingsByCriteria(@RequestBody @Valid TraineeTrainingRequest request){
        List<TrainingResponse> list = traineeService.getTraineeTrainings(request);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
