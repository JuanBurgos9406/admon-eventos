package com.crediservir.gestion.repositories;

import com.crediservir.gestion.models.AsistenteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenteRepository extends JpaRepository<AsistenteModel,Long> {
    List<AsistenteModel> findByEventosComprados_Evento_IdEvento(Long eventoId);
}
