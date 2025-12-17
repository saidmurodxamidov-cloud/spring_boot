package org.example.spring_boot.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Entity
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name should be present")
    private String firstName;

    @NotNull(message = "Last name should not be empty")
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String userName;

    @ToString.Exclude
    private String passwordHash;

    private boolean isActive;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

}
