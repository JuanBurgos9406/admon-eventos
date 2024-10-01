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
public class ListaEsperaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idListaEspera;

    @Column(nullable = false)
    private LocalDateTime fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private EventoModel evento;

    @ManyToOne
    @JoinColumn(name = "id_asistente", nullable = false)
    private AsistenteModel asistente;
}
