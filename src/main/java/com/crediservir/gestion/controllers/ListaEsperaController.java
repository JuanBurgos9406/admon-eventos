package com.crediservir.gestion.controllers;


import com.crediservir.gestion.models.ListaEsperaModel;
import com.crediservir.gestion.services.ListaEsperaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lista-espera")
public class ListaEsperaController {

    @Autowired
    private ListaEsperaService listaEsperaService;

    @GetMapping
    public List<ListaEsperaModel> obtenerTodasLasListasEspera() {
        return listaEsperaService.obtenerTodasLasListasEspera();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaEsperaModel> obtenerListaEsperaPorId(@PathVariable Long id) {
        Optional<ListaEsperaModel> listaEspera = listaEsperaService.obtenerListaEsperaPorId(id);
        return listaEspera.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ListaEsperaModel agregarAListaEspera(@RequestBody ListaEsperaModel listaEspera) {
        return listaEsperaService.agregarAListaEspera(listaEspera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaEsperaModel> actualizarListaEspera(@PathVariable Long id, @RequestBody ListaEsperaModel listaEspera) {
        try {
            ListaEsperaModel listaEsperaActualizada = listaEsperaService.actualizarListaEspera(id, listaEspera);
            return ResponseEntity.ok(listaEsperaActualizada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDeListaEspera(@PathVariable Long id) {
        listaEsperaService.eliminarDeListaEspera(id);
        return ResponseEntity.noContent().build();
    }
}
