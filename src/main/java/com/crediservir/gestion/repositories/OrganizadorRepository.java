package com.crediservir.gestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizadorRepository extends JpaRepository<OrganizadorModel,Long> {

    List<OrganizadorModel>findByEventosOrganizados_IdEvento(Long idEvento);
}
