package com.cursosspringjpa.cursospringudemy.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.cursosspringjpa.cursospringudemy.dto.CategoriaDTO;
import com.cursosspringjpa.cursospringudemy.model.Categoria;
import com.cursosspringjpa.cursospringudemy.service.CategoriaService;

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
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService srvc;

    @GetMapping()
    public ResponseEntity<List<CategoriaDTO>> findAll(){

        List<Categoria> list = srvc.findAll();
        List<CategoriaDTO> listdto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok(listdto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Integer id){

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        srvc.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") int pg, 
            @RequestParam(value = "lines", defaultValue = "24") int linesPerpage, 
            @RequestParam(value = "order", defaultValue = "nome") String orderBy, 
            @RequestParam(value = "direct", defaultValue = "ASC") String direction){

        Page<Categoria> list = srvc.findPage(pg, linesPerpage, orderBy, direction);
        Page<CategoriaDTO> listdto = list.map(obj -> new CategoriaDTO(obj));

        return ResponseEntity.ok(listdto);
    }
}
