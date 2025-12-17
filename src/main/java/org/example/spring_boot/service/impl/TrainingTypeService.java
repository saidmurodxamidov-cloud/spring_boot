package org.example.spring_boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.spring_boot.dto.request.TrainingTypeRequest;
import org.example.spring_boot.dto.response.TrainingTypeResponse;
import org.example.spring_boot.entity.TrainingTypeEntity;
import org.example.spring_boot.repository.TrainingTypeRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingTypeService {
    private final TrainingTypeRepository trainingTypeRepository;

    @Transactional
    @PreAuthorize("hasRole('TRAINEE') or hasRole('ADMIN') or hasRole('TRAINER')")
    public TrainingTypeResponse createTrainingType(TrainingTypeRequest trainingTypeRequest){
        TrainingTypeEntity trainingTypeEntity = TrainingTypeEntity.builder()
                .trainingTypeName(trainingTypeRequest.getTrainingTypeName())
                .build();
        trainingTypeRepository.save(trainingTypeEntity);
        return new TrainingTypeResponse(trainingTypeEntity.getId(),trainingTypeEntity.getTrainingTypeName());
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('TRAINEE') or hasRole('ADMIN') or hasRole('TRAINER')")
    public List<TrainingTypeResponse> getAllTrainingTypes(){
        return trainingTypeRepository.findAll().stream()
                .map(trainingType -> new TrainingTypeResponse(trainingType.getId(),trainingType.getTrainingTypeName())).toList();
    }
}
