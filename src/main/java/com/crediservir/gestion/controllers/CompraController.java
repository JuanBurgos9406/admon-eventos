package com.crediservir.gestion.controllers;

import com.crediservir.gestion.models.CompraModel;
import com.crediservir.gestion.services.CompraService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraModel> crearCompra(@RequestBody CompraModel compra) {
        try {
            CompraModel compraCreada = compraService.crearCompra(compra);
            return ResponseEntity.status(HttpStatus.CREATED).body(compraCreada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<CompraModel>> obtenerTodasLasCompras() {
        List<CompraModel> compras = compraService.obtenerTodasLasCompras();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraModel> obtenerCompraPorId(@PathVariable Long id) {
        Optional<CompraModel> compra = compraService.obtenerCompraPorId(id);
        return compra.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraModel> actualizarCompra(@PathVariable Long id, @RequestBody CompraModel compra) {
        try {
            CompraModel compraActualizada = compraService.actualizarCompra(id, compra);
            return ResponseEntity.ok(compraActualizada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {
        try {
            compraService.eliminarCompra(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/asistente/{idAsistente}")
    public ResponseEntity<List<CompraModel>> obtenerComprasPorAsistente(@PathVariable Long idAsistente) {
        List<CompraModel> compras = compraService.obtenerComprasPorAsistente(idAsistente);
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<List<CompraModel>> obtenerComprasPorEvento(@PathVariable Long idEvento) {
        List<CompraModel> compras = compraService.obtenerComprasPorEvento(idEvento);
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<CompraModel>> obtenerComprasPorFecha(
            @RequestParam LocalDateTime fechaInicio,
            @RequestParam LocalDateTime fechaFin) {
        List<CompraModel> compras = compraService.obtenerComprasPorFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(compras);
    }
}
