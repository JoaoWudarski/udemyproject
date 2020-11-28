package com.cursosspringjpa.cursospringudemy.controller;


import com.cursosspringjpa.cursospringudemy.model.Cliente;
import com.cursosspringjpa.cursospringudemy.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService srvc;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listar(@PathVariable Integer id){

        Cliente cat = srvc.find(id);
        
        return ResponseEntity.ok(cat);
    }

    
}
