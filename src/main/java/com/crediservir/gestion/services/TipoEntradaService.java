package com.crediservir.gestion.services;

import com.crediservir.gestion.models.TipoEntradaModel;
import com.crediservir.gestion.repositories.TipoEntradaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEntradaService {

    @Autowired
    private TipoEntradaRepository tipoEntradaRepository;

        public List<TipoEntradaModel> obtenerTodosLosTiposEntrada() {
        return tipoEntradaRepository.findAll();
    }

       public TipoEntradaModel obtenerTipoEntradaPorId(Long id) {
        return tipoEntradaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de entrada  no encontrado."));
    }


    public TipoEntradaModel crearTipoEntrada(TipoEntradaModel tipoEntrada) {
        return tipoEntradaRepository.save(tipoEntrada);
    }

    public TipoEntradaModel actualizarTipoEntrada(Long id, TipoEntradaModel tipoEntradaActualizado) {
        TipoEntradaModel tipoEntradaExistente = obtenerTipoEntradaPorId(id);

        tipoEntradaExistente.setNombre(tipoEntradaActualizado.getNombre());
        tipoEntradaExistente.setPorcentajeAdicional(tipoEntradaActualizado.getPorcentajeAdicional());
        tipoEntradaExistente.setEvento(tipoEntradaActualizado.getEvento());

        return tipoEntradaRepository.save(tipoEntradaExistente);
    }

    public void eliminarTipoEntrada(Long id) {
        tipoEntradaRepository.deleteById(id);
    }

}
