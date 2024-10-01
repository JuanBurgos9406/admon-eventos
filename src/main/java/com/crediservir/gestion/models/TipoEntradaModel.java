package com.crediservir.gestion.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class TipoEntradaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoEntrada;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double porcentajeAdicional;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private EventoModel evento;

    @OneToMany(mappedBy = "tipoEntrada", cascade = CascadeType.ALL)
    private List<CompraModel> compras;
}
