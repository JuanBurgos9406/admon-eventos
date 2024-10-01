package com.crediservir.gestion.repositories;

import com.crediservir.gestion.models.EventoModel;
import com.crediservir.gestion.models.RegistroEventoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistroEventoRepository extends JpaRepository<RegistroEventoModel,Long> {


    List<RegistroEventoModel> findByAsistente_IdAsistente(Long idAsistente);

    List<RegistroEventoModel> findByEvento_IdEvento(Long idEvento);

    List<RegistroEventoModel> findByFechaCompraBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<RegistroEventoModel> findByCancelado(boolean cancelado);

    @Query("SELECT re.evento FROM RegistroEventoModel re WHERE re.asistente.id = :asistenteId")
    List<EventoModel> findEventosByAsistenteId(@Param("asistenteId") Long asistenteId);
}
