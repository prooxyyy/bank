package me.pro0xy.bankemulation.db.entity;

import jakarta.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:31</p>
 */

@Entity
@Table(name = "bank_users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;
    String lastName;
    String email;

    @NotNull
    @Min(1000)
    @Max(9999)
    @Digits(integer = 4, fraction = 0)
    Integer pin;
    LocalDateTime registeredAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Card> cards;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Collection<Role> roles;
}
