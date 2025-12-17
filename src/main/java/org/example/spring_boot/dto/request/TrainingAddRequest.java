package org.example.spring_boot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingAddRequest {
    private String trainingName;
    private String traineeUsername;
    private String trainerUsername;
    private String trainingType;
    private LocalDate trainingDate;
    private Integer trainingDurationInMinutes;
}
