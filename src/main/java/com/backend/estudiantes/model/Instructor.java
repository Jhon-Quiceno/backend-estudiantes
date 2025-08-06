package com.backend.estudiantes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "instructores")
@Data
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // de uno a uno
    @JoinColumn(name = "usuario_id", unique = true, nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String especialidad;

    @Column(nullable = false)
    private LocalDate fechaContratacion;
}
