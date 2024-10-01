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

public class OrganizadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrganizador;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL)
    private List<EventoModel> eventosOrganizados;
}
