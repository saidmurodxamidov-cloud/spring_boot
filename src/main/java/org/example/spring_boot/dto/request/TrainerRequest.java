package org.example.spring_boot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerRequest {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotNull
    private List<String> specializations;
}
