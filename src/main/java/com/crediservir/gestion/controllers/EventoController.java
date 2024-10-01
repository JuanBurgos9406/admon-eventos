package com.crediservir.gestion.controllers;

import com.crediservir.gestion.models.AsistenteModel;
import com.crediservir.gestion.models.EventoModel;
import com.crediservir.gestion.repositories.EventoRepository;
import com.crediservir.gestion.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoModel> crearEvento(@RequestBody EventoModel eventoModel) {
        EventoModel eventoCreado = eventoService.crearEvento(eventoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoCreado);
    }


    @GetMapping ("/{idEvento}")
    public ResponseEntity<EventoModel> obtenerEventoPorId(@PathVariable Long idEvento) {
        EventoModel evento = eventoService.obtenerEventoPorId(idEvento);
        return ResponseEntity.ok(evento);
    }


    @PutMapping("/{idEvento}")
    public ResponseEntity<EventoModel> actualizarEvento(@PathVariable Long idEvento, @RequestBody EventoModel eventoModel) {
        EventoModel eventoActualizado = eventoService.actualizarEvento(idEvento, eventoModel);
        return ResponseEntity.ok(eventoActualizado);
    }


    @DeleteMapping("/{idEvento}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long idEvento) {
        eventoService.eliminarEvento(idEvento);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<EventoModel>> obtenerTodosLosEventos() {
        List<EventoModel> eventos = eventoService.obtenerTodosLosEventos();
        return ResponseEntity.ok(eventos);
    }


    @GetMapping("/vigentes")
    public ResponseEntity<List<EventoModel>> obtenerEventosVigentes() {
        List<EventoModel> eventosVigentes = eventoService.obtenerEventosVigentes();
        return ResponseEntity.ok(eventosVigentes);
    }


}
