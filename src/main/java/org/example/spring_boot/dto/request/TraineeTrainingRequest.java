package org.example.spring_boot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TraineeTrainingRequest {
    @NotBlank
    private String username;
    private LocalDate from;
    private LocalDate to;
    private String trainerName;
    private String trainingType;
}
