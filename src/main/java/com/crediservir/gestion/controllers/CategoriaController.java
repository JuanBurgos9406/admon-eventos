package com.crediservir.gestion.controllers;


import com.crediservir.gestion.models.CategoriaModel;
import com.crediservir.gestion.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaModel> crearCategoria(@RequestBody CategoriaModel categoria) {
        CategoriaModel categoriaCreada = categoriaService.crearCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCreada);
    }

    // Listar todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoriaModel>> listarCategorias() {
        List<CategoriaModel> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    // Buscar una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaModel> buscarCategoria(@PathVariable Long id) {
        CategoriaModel categoria = categoriaService.buscarCategoria(id);
        return ResponseEntity.ok(categoria);
    }

    // Modificar una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaModel> modificarCategoria(@PathVariable Long id, @RequestBody CategoriaModel detallesCategoria) {
        CategoriaModel categoriaActualizada = categoriaService.modificarCategoria(id, detallesCategoria);
        return ResponseEntity.ok(categoriaActualizada);
    }

    // Eliminar una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id); // Debes agregar este método en tu servicio
        return ResponseEntity.noContent().build();
    }
}
