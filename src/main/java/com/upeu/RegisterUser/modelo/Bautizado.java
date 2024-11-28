package com.upeu.RegisterUser.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "bautizados")
public class Bautizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date fechaBautizo;
    private LocalDate lugar;

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id") // FK a la tabla personas
    private Persona persona;

    @Version
    private int version; // Control de versión para concurrencia optimista
}
