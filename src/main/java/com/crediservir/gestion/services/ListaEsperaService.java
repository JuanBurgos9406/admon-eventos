package com.crediservir.gestion.services;

import com.crediservir.gestion.models.CompraModel;
import com.crediservir.gestion.models.ListaEsperaModel;
import com.crediservir.gestion.repositories.ListaEsperaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ListaEsperaService {

    @Autowired
    private ListaEsperaRepository listaEsperaRepository;


    public List<ListaEsperaModel> obtenerTodasLasListasEspera() {
        return listaEsperaRepository.findAll();
    }

    public Optional<ListaEsperaModel> obtenerListaEsperaPorId(Long id) {
        return listaEsperaRepository.findById(id);
    }

    public ListaEsperaModel agregarAListaEspera(ListaEsperaModel listaEspera) {
        listaEspera.setFechaIngreso(LocalDateTime.now()); // Establecer la fecha de registro actual
        return listaEsperaRepository.save(listaEspera);
    }

    public ListaEsperaModel actualizarListaEspera(Long id, ListaEsperaModel listaEspera) {
        Optional<ListaEsperaModel> listaEsperaExistente = listaEsperaRepository.findById(id);
        if (listaEsperaExistente.isPresent()) {
            ListaEsperaModel listaEsperaActualizada = listaEsperaExistente.get();
            listaEsperaActualizada.setEvento(listaEspera.getEvento());
            listaEsperaActualizada.setAsistente(listaEspera.getAsistente());
            return listaEsperaRepository.save(listaEsperaActualizada);
        } else {
            throw new EntityNotFoundException("Lista de espera no encontrada.");
        }
    }

    public void eliminarDeListaEspera(Long id) {
        listaEsperaRepository.deleteById(id);
    }

    public List<ListaEsperaModel> obtenerListasEsperaPorEvento(Long idEvento) {
        return listaEsperaRepository.findByEvento_IdEvento(idEvento);
    }

    public List<ListaEsperaModel> obtenerListasEsperaPorAsistente(Long idAsistente) {
        return listaEsperaRepository.findByAsistente_IdAsistente(idAsistente);
    }


}
