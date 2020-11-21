package com.cursosspringjpa.cursospringudemy.service;

import java.util.Optional;

import com.cursosspringjpa.cursospringudemy.model.Categoria;
import com.cursosspringjpa.cursospringudemy.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository rep;

    public Categoria buscar(Integer id){
        Optional<Categoria> op = rep.findById(id);
        
        return op.orElseThrow( () -> new ResponseStatusException
                             (HttpStatus.NOT_FOUND, "Categoria não encontrada, ID = "
                              + id + " Tipo: " + Categoria.class.getName()));
    } 
}
