package com.cursosspringjpa.cursospringudemy.controller;


import com.cursosspringjpa.cursospringudemy.model.Pedido;
import com.cursosspringjpa.cursospringudemy.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService srvc;

    @GetMapping("/{id}")
    public ResponseEntity<?> listar(@PathVariable Integer id){
        Pedido cat = srvc.buscar(id);
        
        return ResponseEntity.ok(cat);
    }

    
}
