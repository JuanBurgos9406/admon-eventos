package com.crediservir.gestion.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CodigoPromocionalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCodigo;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private double porcentajeDescuento;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private LocalDateTime fechaFin;

    @Column(nullable = false)
    private boolean activo;
}
