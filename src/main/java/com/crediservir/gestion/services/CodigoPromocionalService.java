package com.crediservir.gestion.services;

import com.crediservir.gestion.models.CodigoPromocionalModel;
import com.crediservir.gestion.repositories.CodigoPromocionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CodigoPromocionalService {

    @Autowired
    private CodigoPromocionalRepository codigoPromocionalRepository;

    public List<CodigoPromocionalModel> obtenerTodosLosCodigos() {
        return codigoPromocionalRepository.findAll();
    }

    public Optional<CodigoPromocionalModel> obtenerCodigoPorId(Long id) {
        return codigoPromocionalRepository.findById(id);
    }

    public CodigoPromocionalModel crearCodigoPromocional(CodigoPromocionalModel codigoPromocional) {
        return codigoPromocionalRepository.save(codigoPromocional);
    }

    public CodigoPromocionalModel actualizarCodigoPromocional(Long id, CodigoPromocionalModel codigoPromocional) {
        Optional<CodigoPromocionalModel> codigoExistente = codigoPromocionalRepository.findById(id);
        if (codigoExistente.isPresent()) {
            CodigoPromocionalModel codigoActualizado = codigoExistente.get();
            codigoActualizado.setCodigo(codigoPromocional.getCodigo());
            codigoActualizado.setPorcentajeDescuento(codigoPromocional.getPorcentajeDescuento());
            codigoActualizado.setFechaInicio(codigoPromocional.getFechaInicio());
            codigoActualizado.setFechaFin(codigoPromocional.getFechaFin());
            codigoActualizado.setActivo(codigoPromocional.isActivo());
            return codigoPromocionalRepository.save(codigoActualizado);
        } else {
            throw new EntityNotFoundException("Código promocional no encontrado.");
        }
    }

    public void eliminarCodigoPromocional(Long id) {
        codigoPromocionalRepository.deleteById(id);
    }

    public List<CodigoPromocionalModel> obtenerCodigosActivos() {
        return codigoPromocionalRepository.findByActivoTrue();
    }

    public List<CodigoPromocionalModel> obtenerCodigosValidos(LocalDateTime fechaActual) {
        return codigoPromocionalRepository.findByFechaInicioBeforeAndFechaFinAfter(fechaActual, fechaActual);
    }
}
