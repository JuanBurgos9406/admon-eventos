package com.crediservir.gestion.repositories;

import com.crediservir.gestion.models.EventoModel;
import com.crediservir.gestion.models.ListaEsperaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ListaEsperaRepository extends JpaRepository<ListaEsperaModel,Long> {

    Optional<ListaEsperaModel> findFirstByEventoOrderByFechaIngresoAsc(EventoModel evento);

    List<ListaEsperaModel> findByEvento_IdEvento(Long idEvento);

    List<ListaEsperaModel> findByAsistente_IdAsistente(Long idAsistente);
}
