package com.crediservir.gestion.repositories;

import com.crediservir.gestion.models.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CompraRepository extends JpaRepository<CompraModel,Long> {
    List<CompraModel> findByAsistente_IdAsistente(Long idAsistente);

    List<CompraModel> findByEvento_IdEvento(Long idEvento);


    List<CompraModel> findByFechaCompraBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);



    @Query("SELECT COUNT(c) FROM CompraModel c WHERE c.evento.id = :idEvento")
    long countByEventoId(@Param("idEvento") Long idEvento);
}
