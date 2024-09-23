package com.hackifytech.edu.models;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "roles")
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 60,unique = true, nullable = false)
    private String name;
}
