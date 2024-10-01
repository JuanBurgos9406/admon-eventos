package com.crediservir.gestion.services;

import com.crediservir.gestion.models.*;
import com.crediservir.gestion.repositories.CompraRepository;
import com.crediservir.gestion.repositories.EventoRepository;
import com.crediservir.gestion.repositories.ListaEsperaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ListaEsperaRepository listaEsperaRepository;

    @Autowired
    private EventoRepository eventoRepository;


    public CompraModel crearCompra(CompraModel compra) {
        // Establecer la fecha de compra si es nula
        if (compra.getFechaCompra() == null) {
            compra.setFechaCompra(LocalDateTime.now());
        }

        EventoModel evento = validarEvento(compra.getEvento());

        TipoEntradaModel tipoEntrada = validarTipoEntrada(compra.getTipoEntrada());

        double totalCompra = calcularTotalCompra(evento, tipoEntrada, compra.getCodigosAplicados());

        // Establecer el total a pagar y el evento en la compra
        compra.setTotalAPagar(totalCompra);
        compra.setEvento(evento);

        return compraRepository.save(compra);
    }

    private EventoModel validarEvento(EventoModel evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo.");
        }
        return evento;
    }


    private TipoEntradaModel validarTipoEntrada(TipoEntradaModel tipoEntrada) {
        if (tipoEntrada == null) {
            throw new IllegalArgumentException("El tipo de entrada no puede ser nulo.");
        }
        return tipoEntrada;
    }

    private double calcularTotalCompra(EventoModel evento, TipoEntradaModel tipoEntrada, List<CodigoPromocionalModel> codigos) {
        double valorBase = evento.getValorBase();
        double valorAdicional = tipoEntrada.getPorcentajeAdicional();
        double totalCompra = valorBase + (valorBase * (valorAdicional / 100));

       totalCompra = aplicarCodigosPromocionales(totalCompra, codigos);

        // Aplicar el valor mínimo permitido (70% del valor base)
        double valorMinimo = valorBase * 0.70;
        return Math.max(totalCompra, valorMinimo);
    }

    private double aplicarCodigosPromocionales(double totalCompra, List<CodigoPromocionalModel> codigos) {
        if (codigos != null && !codigos.isEmpty()) {
            if (codigos.size() > 2) {
                throw new IllegalArgumentException("Solo se permite ingresar como máximo dos códigos de descuento.");
            }

            for (CodigoPromocionalModel codigo : codigos) {
                if (codigo.isActivo() &&
                        LocalDateTime.now().isAfter(codigo.getFechaInicio()) &&
                        LocalDateTime.now().isBefore(codigo.getFechaFin())) {
                    double descuento = codigo.getPorcentajeDescuento();
                    totalCompra -= totalCompra * (descuento / 100);
                } else {
                    throw new IllegalArgumentException("El código promocional no es válido o ha expirado.");
                }
            }
        }
        return totalCompra;
    }


    public List<CompraModel> obtenerTodasLasCompras() {
        return compraRepository.findAll();
    }

    public Optional<CompraModel> obtenerCompraPorId(Long id) {
        return compraRepository.findById(id);
    }

    public CompraModel actualizarCompra(Long id, CompraModel compra) {
        Optional<CompraModel> compraExistente = compraRepository.findById(id);
        if (compraExistente.isPresent()) {
            CompraModel compraActualizada = compraExistente.get();
            compraActualizada.setTotalAPagar(compra.getTotalAPagar());
            compraActualizada.setCodigosAplicados(compra.getCodigosAplicados());
            compraActualizada.setAsistente(compra.getAsistente());
            compraActualizada.setEvento(compra.getEvento());
            compraActualizada.setTipoEntrada(compra.getTipoEntrada());
            return compraRepository.save(compraActualizada);
        } else {
            throw new EntityNotFoundException("Compra no encontrada.");
        }
    }

    public void eliminarCompra(Long id) {
        Optional<CompraModel> compraExistente = compraRepository.findById(id);

        if (compraExistente.isPresent()) {
            CompraModel compra = compraExistente.get();

            EventoModel evento = compra.getEvento();
            if (evento != null) {
                evento.setCupoDisponible(evento.getCupoDisponible() + 1);
                eventoRepository.save(evento); // Guardar el evento actualizado
            }

            compraRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Compra no encontrada.");
        }
    }

    public List<CompraModel> obtenerComprasPorAsistente(Long idAsistente) {
        return compraRepository.findByAsistente_IdAsistente(idAsistente);
    }

    public List<CompraModel> obtenerComprasPorEvento(Long idEvento) {
        return compraRepository.findByEvento_IdEvento(idEvento);
    }

    public List<CompraModel> obtenerComprasPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return compraRepository.findByFechaCompraBetween(fechaInicio, fechaFin);
    }
}
