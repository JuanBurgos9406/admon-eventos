package com.crediservir.gestion.controllers;

import com.crediservir.gestion.models.TipoEntradaModel;
import com.crediservir.gestion.services.TipoEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TiposEntradas")
public class TipoEntradaController {

    @Autowired
    private TipoEntradaService tipoEntradaService;

    // Crear un nuevo tipo de entrada
    @PostMapping
    public ResponseEntity<TipoEntradaModel> crearTipoEntrada(@RequestBody TipoEntradaModel tipoEntrada) {
        TipoEntradaModel tipoEntradaCreado = tipoEntradaService.crearTipoEntrada(tipoEntrada);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoEntradaCreado);
    }

    // Listar todos los tipos de entrada
    @GetMapping
    public ResponseEntity<List<TipoEntradaModel>> obtenerTodosLosTiposEntrada() {
        List<TipoEntradaModel> tiposEntrada = tipoEntradaService.obtenerTodosLosTiposEntrada();
        return ResponseEntity.ok(tiposEntrada);
    }

    // Buscar un tipo de entrada por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoEntradaModel> obtenerTipoEntradaPorId(@PathVariable Long id) {
        TipoEntradaModel tipoEntrada = tipoEntradaService.obtenerTipoEntradaPorId(id);
        return ResponseEntity.ok(tipoEntrada);
    }

    // Modificar un tipo de entrada existente
    @PutMapping("/{id}")
    public ResponseEntity<TipoEntradaModel> actualizarTipoEntrada(@PathVariable Long id, @RequestBody TipoEntradaModel tipoEntradaActualizado) {
        TipoEntradaModel tipoEntradaModificado = tipoEntradaService.actualizarTipoEntrada(id, tipoEntradaActualizado);
        return ResponseEntity.ok(tipoEntradaModificado);
    }

    // Eliminar un tipo de entrada
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoEntrada(@PathVariable Long id) {
        tipoEntradaService.eliminarTipoEntrada(id);
        return ResponseEntity.noContent().build();
    }


}
