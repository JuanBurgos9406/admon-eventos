package com.crediservir.gestion.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @Column(nullable = false)
    private String nombreEvento;

    private String descrpcionEvento;

    @Column(nullable = false)
    private LocalDateTime fechaHoraEvento;

    @Column(nullable = false)
    private String lugar;

    @Column(nullable = false)
    private Long cupoDisponible;

    @Column(nullable = false)
    private String tipoEvento;

    private double valorBase;

    @Column(nullable = false)
    private LocalDateTime fechaCierre;

    @Column(nullable = false)
    private LocalDateTime fechaApertura;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaModel categoria;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<RegistroEventoModel> asistentesRegistrados;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_evento")
    private List<TipoEntradaModel> tiposEntradas;
}
