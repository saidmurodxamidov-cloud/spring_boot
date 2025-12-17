package org.example.spring_boot.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TraineeProfileResponse {
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String address;
    boolean isActive;
    List<TrainingTypeResponse> specialization;
    List<TrainerResponse> trainers;
}
