package org.example.spring_boot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "training_type")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TrainingTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String trainingTypeName;

    @ManyToMany(mappedBy = "specializations")
    @ToString.Exclude
    private Set<TrainerEntity> trainerEntities = new HashSet<>();

    @OneToMany(mappedBy = "trainingType")
    private Set<TrainingEntity> trainings = new HashSet<>();

}
