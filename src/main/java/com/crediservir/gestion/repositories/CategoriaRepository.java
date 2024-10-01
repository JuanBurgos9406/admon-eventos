package com.crediservir.gestion.repositories;

import com.crediservir.gestion.models.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaModel,Long> {
}
