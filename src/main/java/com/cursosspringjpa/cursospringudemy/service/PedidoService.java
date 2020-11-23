package com.cursosspringjpa.cursospringudemy.service;

import java.util.Optional;

import com.cursosspringjpa.cursospringudemy.model.Pedido;
import com.cursosspringjpa.cursospringudemy.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository rep;

    public Pedido buscar(Integer id){
        Optional<Pedido> op = rep.findById(id);
        
        return op.orElseThrow( () -> new ResponseStatusException
                             (HttpStatus.NOT_FOUND, "Pedido não encontrada, ID = "
                              + id + " Tipo: " + Pedido.class.getName()));
    } 
}
