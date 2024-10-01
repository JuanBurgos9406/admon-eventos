package com.crediservir.gestion.repositories;

import com.crediservir.gestion.models.CodigoPromocionalModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CodigoPromocionalRepository extends JpaRepository<CodigoPromocionalModel,Long> {

    List<CodigoPromocionalModel> findByFechaInicioBeforeAndFechaFinAfter(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<CodigoPromocionalModel> findByActivoTrue();
}
