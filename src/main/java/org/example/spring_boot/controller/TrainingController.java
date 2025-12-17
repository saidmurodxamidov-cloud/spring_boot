package org.example.spring_boot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_boot.dto.request.TrainingAddRequest;
import org.example.spring_boot.service.impl.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/training")
public class TrainingController {
    private final TrainingService trainingService;

    @PostMapping
    public ResponseEntity<Void> createTraining(@RequestBody @Valid TrainingAddRequest request){
        trainingService.addTraining(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
