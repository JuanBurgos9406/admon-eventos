package com.crediservir.gestion.controllers;

import com.crediservir.gestion.models.CodigoPromocionalModel;
import com.crediservir.gestion.services.CodigoPromocionalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/codigospromocionales")
public class CodigoPromocionalController {

    @Autowired
    private CodigoPromocionalService codigoPromocionalService;

    // Crear un nuevo código promocional
    @PostMapping
    public ResponseEntity<CodigoPromocionalModel> crearCodigoPromocional(@RequestBody CodigoPromocionalModel codigoPromocional) {
        CodigoPromocionalModel codigoCreado = codigoPromocionalService.crearCodigoPromocional(codigoPromocional);
        return ResponseEntity.status(HttpStatus.CREATED).body(codigoCreado);
    }

    // Listar todos los códigos promocionales
    @GetMapping
    public ResponseEntity<List<CodigoPromocionalModel>> obtenerTodosLosCodigos() {
        List<CodigoPromocionalModel> codigos = codigoPromocionalService.obtenerTodosLosCodigos();
        return ResponseEntity.ok(codigos);
    }

    // Buscar un código promocional por ID
    @GetMapping("/{id}")
    public ResponseEntity<CodigoPromocionalModel> obtenerCodigoPorId(@PathVariable Long id) {
        CodigoPromocionalModel codigoPromocional = codigoPromocionalService.obtenerCodigoPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Código promocional no encontrado."));
        return ResponseEntity.ok(codigoPromocional);
    }

    // Modificar un código promocional existente
    @PutMapping("/{id}")
    public ResponseEntity<CodigoPromocionalModel> actualizarCodigoPromocional(@PathVariable Long id, @RequestBody CodigoPromocionalModel codigoPromocionalActualizado) {
        CodigoPromocionalModel codigoModificado = codigoPromocionalService.actualizarCodigoPromocional(id, codigoPromocionalActualizado);
        return ResponseEntity.ok(codigoModificado);
    }

    // Eliminar un código promocional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCodigoPromocional(@PathVariable Long id) {
        codigoPromocionalService.eliminarCodigoPromocional(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener códigos promocionales activos
    @GetMapping("/activos")
    public ResponseEntity<List<CodigoPromocionalModel>> obtenerCodigosActivos() {
        List<CodigoPromocionalModel> codigosActivos = codigoPromocionalService.obtenerCodigosActivos();
        return ResponseEntity.ok(codigosActivos);
    }

    // Obtener códigos promocionales válidos según la fecha actual
    @GetMapping("/validos")
    public ResponseEntity<List<CodigoPromocionalModel>> obtenerCodigosValidos(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaActual) {
        List<CodigoPromocionalModel> codigosValidos = codigoPromocionalService.obtenerCodigosValidos(fechaActual);
        return ResponseEntity.ok(codigosValidos);
    }

}
