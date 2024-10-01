package com.crediservir.gestion.services;

import com.crediservir.gestion.models.EventoModel;
import com.crediservir.gestion.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;


    public EventoModel crearEvento(EventoModel eventoModel) {
        return eventoRepository.save(eventoModel);
    }


    public EventoModel actualizarEvento(Long idEvento, EventoModel eventoModel) {
        EventoModel eventoExistente = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("El evento no existe"));

        eventoExistente.setNombreEvento(eventoModel.getNombreEvento());
        eventoExistente.setDescrpcionEvento(eventoModel.getDescrpcionEvento());
        eventoExistente.setFechaHoraEvento(eventoModel.getFechaHoraEvento());
        eventoExistente.setLugar(eventoModel.getLugar());
        eventoExistente.setCupoDisponible(eventoModel.getCupoDisponible());
        eventoExistente.setTipoEvento(eventoModel.getTipoEvento());
        eventoExistente.setValorBase(eventoModel.getValorBase());
        eventoExistente.setFechaCierre(eventoModel.getFechaCierre());
        eventoExistente.setFechaApertura(eventoModel.getFechaApertura());
        eventoExistente.setCategoria(eventoModel.getCategoria());

        return eventoRepository.save(eventoExistente);
    }

    public EventoModel obtenerEventoPorId(Long idEvento) {
        return eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("El evento no existe"));
    }

    public void eliminarEvento(Long idEvento) {
        eventoRepository.deleteById(idEvento);
    }

    public List<EventoModel> obtenerEventosVigentes() {
        LocalDateTime ahora = LocalDateTime.now();
        return eventoRepository.findByFechaCierre(ahora);
    }

    public List<EventoModel> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }



}
