package com.cursosspringjpa.cursospringudemy.service;

import java.util.Optional;

import com.cursosspringjpa.cursospringudemy.model.Cliente;
import com.cursosspringjpa.cursospringudemy.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository rep;

    public Cliente find(Integer id){
        Optional<Cliente> op = rep.findById(id);
        
        return op.orElseThrow( () -> new ResponseStatusException
                             (HttpStatus.NOT_FOUND, "Cliente não encontrada, ID = "
                              + id + " Tipo: " + Cliente.class.getName()));
    } 
}
