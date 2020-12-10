package com.cursosspringjpa.cursospringudemy.controller;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.cursosspringjpa.cursospringudemy.dto.ClienteDTO;
import com.cursosspringjpa.cursospringudemy.dto.ClienteNewDTO;
import com.cursosspringjpa.cursospringudemy.model.Cliente;
import com.cursosspringjpa.cursospringudemy.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService srvc;

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> findAll(){

        List<Cliente> list = srvc.findAll();
        List<ClienteDTO> listdto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok(listdto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id){

        Cliente cat = srvc.find(id);
        
        return ResponseEntity.ok(cat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objdto){
        Cliente obj = srvc.fromDTO(objdto);
        obj.setId(id);
        obj = srvc.update(obj);

        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        srvc.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") int pg, 
            @RequestParam(value = "lines", defaultValue = "24") int linesPerpage, 
            @RequestParam(value = "order", defaultValue = "nome") String orderBy, 
            @RequestParam(value = "direct", defaultValue = "ASC") String direction){

        Page<Cliente> list = srvc.findPage(pg, linesPerpage, orderBy, direction);
        Page<ClienteDTO> listdto = list.map(obj -> new ClienteDTO(obj));

        return ResponseEntity.ok(listdto);
    }

    @PostMapping()
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objdto){
        Cliente obj = srvc.fromDTO(objdto);
        obj = srvc.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    
        return ResponseEntity.created(uri).build();
    }
    
}
