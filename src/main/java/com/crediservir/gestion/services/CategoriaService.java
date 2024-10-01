package com.crediservir.gestion.services;


import com.crediservir.gestion.models.CategoriaModel;
import com.crediservir.gestion.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public CategoriaModel crearCategoria(CategoriaModel categoria){
        return categoriaRepository.save(categoria);
    }

    public List<CategoriaModel>listarCategorias(){
        return categoriaRepository.findAll();
    }

    public CategoriaModel buscarCategoria(Long id){
        return categoriaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("categoria no encontrada"));
    }

    public CategoriaModel modificarCategoria(Long id, CategoriaModel detallesCategoria){
        CategoriaModel categoriaBuscada =categoriaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("El evento no existe"));
        categoriaBuscada.setNombre(detallesCategoria.getNombre());

        return categoriaRepository.save(categoriaBuscada);
    }

    public void eliminarCategoria(Long id) {
         categoriaRepository.deleteById(id);
    }
}
