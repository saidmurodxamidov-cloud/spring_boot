package org.example.spring_boot.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@Table(name = "trainee")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraineeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @PastOrPresent
    private LocalDate dateOfBirth;

    private String address;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany(mappedBy = "trainees")
    private Set<TrainerEntity> trainers = new HashSet<>();

    @OneToMany(mappedBy = "trainee", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<TrainingEntity> trainings = new HashSet<>();
}
