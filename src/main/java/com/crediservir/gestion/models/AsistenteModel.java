package com.crediservir.gestion.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class AsistenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsistente;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String celular;

    @OneToMany(mappedBy = "asistente", cascade = CascadeType.ALL)
    private List<RegistroEventoModel> eventosComprados;

    @OneToMany(mappedBy = "asistente", cascade = CascadeType.ALL)
    private List<CompraModel> comprasRealizadas;
}