package com.crediservir.gestion.services;

import com.crediservir.gestion.models.AsistenteModel;
import com.crediservir.gestion.models.EventoModel;
import com.crediservir.gestion.repositories.AsistenteRepository;
import com.crediservir.gestion.repositories.RegistroEventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenteService {

    @Autowired
    private AsistenteRepository asistenteRepository;

    @Autowired
    private RegistroEventoRepository registroEventoRepository;

    public List<AsistenteModel> obtenerTodosLosAsistentes() {
        return asistenteRepository.findAll();
    }

    public Optional<AsistenteModel> obtenerAsistentePorId(Long id) {
        return asistenteRepository.findById(id);
    }

    public AsistenteModel crearAsistente(AsistenteModel asistente) {
        return asistenteRepository.save(asistente);
    }

    public AsistenteModel actualizarAsistente(Long id, AsistenteModel asistente) {
        Optional<AsistenteModel> asistenteExistente = asistenteRepository.findById(id);
        if (asistenteExistente.isPresent()) {
            AsistenteModel asistenteActualizado = asistenteExistente.get();
            asistenteActualizado.setNombres(asistente.getNombres());
            asistenteActualizado.setApellidos(asistente.getApellidos());
            asistenteActualizado.setFechaNacimiento(asistente.getFechaNacimiento());
            asistenteActualizado.setEmail(asistente.getEmail());
            asistenteActualizado.setCelular(asistente.getCelular());
            // Puedes actualizar también las listas si es necesario
            return asistenteRepository.save(asistenteActualizado);
        } else {
            throw new EntityNotFoundException("Asistente no encontrado.");
        }
    }

    public void eliminarAsistente(Long id) {
        asistenteRepository.deleteById(id);
    }

    public List<EventoModel> obtenerEventosComprados(Long idAsistente) {
        return registroEventoRepository.findEventosByAsistenteId(idAsistente);
    }

    public List<AsistenteModel> findAsistentesByEventoId(Long eventoId) {
        return asistenteRepository.findByEventosComprados_Evento_IdEvento(eventoId);
    }
}
