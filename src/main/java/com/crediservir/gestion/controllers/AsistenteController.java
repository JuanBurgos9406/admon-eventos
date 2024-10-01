package com.crediservir.gestion.controllers;

import com.crediservir.gestion.models.AsistenteModel;
import com.crediservir.gestion.models.EventoModel;
import com.crediservir.gestion.services.AsistenteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asistentes")
public class AsistenteController {

        @Autowired
        private AsistenteService asistenteService;

        // Obtener todos los asistentes
        @GetMapping
        public ResponseEntity<List<AsistenteModel>> obtenerTodosLosAsistentes() {
            List<AsistenteModel> asistentes = asistenteService.obtenerTodosLosAsistentes();
            return ResponseEntity.ok(asistentes);
        }


        @GetMapping("/{id}")
        public ResponseEntity<AsistenteModel> obtenerAsistentePorId(@PathVariable Long id) {
            Optional<AsistenteModel> asistente = asistenteService.obtenerAsistentePorId(id);
            return asistente.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }

        @PostMapping
        public ResponseEntity<AsistenteModel> crearAsistente(@RequestBody AsistenteModel asistente) {
            AsistenteModel nuevoAsistente = asistenteService.crearAsistente(asistente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAsistente);
        }

        @PutMapping("/{id}")
        public ResponseEntity<AsistenteModel> actualizarAsistente(@PathVariable Long id, @RequestBody AsistenteModel asistente) {
            try {
                AsistenteModel asistenteActualizado = asistenteService.actualizarAsistente(id, asistente);
                return ResponseEntity.ok(asistenteActualizado);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarAsistente(@PathVariable Long id) {
            asistenteService.eliminarAsistente(id);
            return ResponseEntity.noContent().build();
        }


    @GetMapping("/{id}/eventos-comprados")
    public ResponseEntity<List<EventoModel>> obtenerEventosComprados(@PathVariable Long id) {
        List<EventoModel> eventosComprados = asistenteService.obtenerEventosComprados(id);
        return ResponseEntity.ok(eventosComprados);
    }

    @GetMapping("/evento/{eventoId}")
    public List<AsistenteModel> getAsistentesByEvento(@PathVariable Long eventoId) {
        return asistenteService.findAsistentesByEventoId(eventoId);
    }


}
