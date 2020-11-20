package com.cursosspringjpa.cursospringudemy.controller;


import com.cursosspringjpa.cursospringudemy.model.Categoria;
import com.cursosspringjpa.cursospringudemy.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService srvc;

    @GetMapping("/{id}")
    public ResponseEntity<?> listar(@PathVariable Integer id){

        Categoria cat = srvc.buscar(id);
        
        return ResponseEntity.ok(cat);
    }
}
