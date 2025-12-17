package org.example.spring_boot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeRegistrationRequest {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    private LocalDate dateOfBirth;
    private String address;
}

