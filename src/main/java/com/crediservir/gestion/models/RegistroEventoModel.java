package com.crediservir.gestion.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class RegistroEventoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistro;

    @Column(nullable = false)
    private LocalDateTime fechaCompra;

    private boolean cancelado;

    @ManyToOne
    @JoinColumn(name = "id_asistente", nullable = false)
    private AsistenteModel asistente;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private EventoModel evento;


}
