package com.crediservir.gestion.services;

import com.crediservir.gestion.models.RegistroEventoModel;
import com.crediservir.gestion.repositories.RegistroEventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class RegistroEventosService {

    @Autowired
    private RegistroEventoRepository registroEventoRepository;

    // Obtener todos los registros de eventos
    public List<RegistroEventoModel> obtenerTodosLosRegistros() {
        return registroEventoRepository.findAll();
    }


    public RegistroEventoModel obtenerRegistroPorId(Long id) {
        return registroEventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de evento no encontrado."));
    }


    public RegistroEventoModel crearRegistroEvento(RegistroEventoModel registroEvento) {
        return registroEventoRepository.save(registroEvento);
    }


    public RegistroEventoModel actualizarRegistroEvento(Long id, RegistroEventoModel registroActualizado) {
        RegistroEventoModel registroExistente = obtenerRegistroPorId(id);

        registroExistente.setFechaCompra(registroActualizado.getFechaCompra());
        registroExistente.setCancelado(registroActualizado.isCancelado());
        registroExistente.setAsistente(registroActualizado.getAsistente());
        registroExistente.setEvento(registroActualizado.getEvento());

        return registroEventoRepository.save(registroExistente);
    }


    public void eliminarRegistroEvento(Long id) {
        registroEventoRepository.deleteById(id);
    }


    public List<RegistroEventoModel> obtenerRegistrosPorAsistente(Long idAsistente) {
        return registroEventoRepository.findByAsistente_IdAsistente(idAsistente);
    }


    public List<RegistroEventoModel> obtenerRegistrosPorEvento(Long idEvento) {
        return registroEventoRepository.findByEvento_IdEvento(idEvento);
    }


    public List<RegistroEventoModel> obtenerRegistrosPorFechaCompra(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return registroEventoRepository.findByFechaCompraBetween(fechaInicio, fechaFin);
    }


    public List<RegistroEventoModel> obtenerRegistrosPorCancelado(boolean cancelado) {
        return registroEventoRepository.findByCancelado(cancelado);
    }


}
