package com.crediservir.gestion.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    public List<OrganizadorModel> obtenerTodosLosOrganizadores() {
        return organizadorRepository.findAll();
    }

    public Optional<OrganizadorModel> obtenerOrganizadorPorId(Long id) {
        return organizadorRepository.findById(id);
    }

    public OrganizadorModel crearOrganizador(OrganizadorModel organizador) {
        return organizadorRepository.save(organizador);
    }

    public OrganizadorModel actualizarOrganizador(Long id, OrganizadorModel organizador) {
        Optional<OrganizadorModel> organizadorExistente = organizadorRepository.findById(id);
        if (organizadorExistente.isPresent()) {
            OrganizadorModel organizadorActualizado = organizadorExistente.get();
            organizadorActualizado.setNombres(organizador.getNombres());
            organizadorActualizado.setApellidos(organizador.getApellidos());
            organizadorActualizado.setEmail(organizador.getEmail());
            organizadorActualizado.setTelefono(organizador.getTelefono());
            return organizadorRepository.save(organizadorActualizado);
        } else {
            throw new EntityNotFoundException("Organizador con ID " + id + " no encontrado.");
        }
    }

    public void eliminarOrganizador(Long id) {
        organizadorRepository.deleteById(id);
    }

    public List<OrganizadorModel> obtenerOrganizadoresPorEvento(Long idEvento) {
        return organizadorRepository.findByEventosOrganizados_IdEvento(idEvento);
    }



}
