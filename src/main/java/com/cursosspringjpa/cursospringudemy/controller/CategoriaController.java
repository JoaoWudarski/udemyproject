package com.cursosspringjpa.cursospringudemy.controller;

import java.net.URI;

import com.cursosspringjpa.cursospringudemy.model.Categoria;
import com.cursosspringjpa.cursospringudemy.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService srvc;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> listar(@PathVariable Integer id){

        Categoria cat = srvc.find(id);
        
        return ResponseEntity.ok(cat);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categoria obj){
        obj = srvc.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria obj){
        obj.setId(id);
        obj = srvc.update(obj);

        return ResponseEntity.ok(obj);
    }
}
