package me.pro0xy.bankemulation.db.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 02:22</p>
 */

@Entity
@Data
@Table(name = "bank_roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;
}
