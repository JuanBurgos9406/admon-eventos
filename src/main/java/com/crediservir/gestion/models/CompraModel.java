package com.crediservir.gestion.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;

    private LocalDateTime fechaCompra;

    private double totalAPagar;

    @ManyToMany
    @JoinTable(
            name = "compra_codigos",
            joinColumns = @JoinColumn(name = "id_compra"),
            inverseJoinColumns = @JoinColumn(name = "id_codigo"))
    private List<CodigoPromocionalModel> codigosAplicados;

    @ManyToOne
    @JoinColumn(name = "id_asistente", nullable = false)
    private AsistenteModel asistente;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private EventoModel evento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_entrada", nullable = false)
    private TipoEntradaModel tipoEntrada;
}
