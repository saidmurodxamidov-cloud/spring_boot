package org.example.spring_boot.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring_boot.dto.request.TrainingTypeRequest;
import org.example.spring_boot.dto.response.TrainingTypeResponse;
import org.example.spring_boot.service.impl.TrainingTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/training-type"))
@RequiredArgsConstructor
public class TrainingTypeController {
    private final TrainingTypeService trainingTypeService;

    @PostMapping
    public ResponseEntity<?> createTrainingType(@Validated @RequestBody TrainingTypeRequest trainingTypeRequest){
        TrainingTypeResponse trainingTypeResponse = trainingTypeService.createTrainingType(trainingTypeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainingTypeResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTrainingTypes(){
        List<TrainingTypeResponse> trainingTypes = trainingTypeService.getAllTrainingTypes();
        return ResponseEntity.status(HttpStatus.OK).body(trainingTypes);
    }
}
