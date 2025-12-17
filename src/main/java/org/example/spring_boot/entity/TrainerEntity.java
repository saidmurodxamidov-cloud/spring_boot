package org.example.spring_boot.entites;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainer")
@Builder
public class TrainerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity user;

    @ManyToMany
    @JoinTable(
            name = "trainer_specializations",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "training_type_id")
    )
    private Set<TrainingTypeEntity> specializations = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "trainers_trainee",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "trainee_id")
    )
    @ToString.Exclude
    private Set<TraineeEntity> trainees = new HashSet<>();

    @OneToMany(mappedBy = "trainer")
    private Set<TrainingEntity> trainings = new HashSet<>();
}