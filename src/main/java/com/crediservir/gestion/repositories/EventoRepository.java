package com.crediservir.gestion.repositories;

import com.crediservir.gestion.models.EventoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository <EventoModel,Long> {


    List<EventoModel> findByFechaCierre(LocalDateTime fechaCierre);


}
