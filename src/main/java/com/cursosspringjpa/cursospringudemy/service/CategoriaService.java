package com.cursosspringjpa.cursospringudemy.service;

import java.util.List;
import java.util.Optional;

import com.cursosspringjpa.cursospringudemy.dto.CategoriaDTO;
import com.cursosspringjpa.cursospringudemy.model.Categoria;
import com.cursosspringjpa.cursospringudemy.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository rep;

    public Categoria find(Integer id){
        Optional<Categoria> op = rep.findById(id);
        
        return op.orElseThrow( () -> new ResponseStatusException
                             (HttpStatus.NOT_FOUND, "Categoria não encontrada, ID = "
                              + id + " Tipo: " + Categoria.class.getName()));
    } 

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return rep.save(obj);
    }

    public Categoria update(Categoria obj){
        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);

        return rep.save(newObj);
    }

    public void delete(Integer id){
        try {
            rep.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Não é possível excluir categoria que possui produtos"));
        }
        
    }

    public List<Categoria> findAll(){
        return rep.findAll();
    }

    public Page<Categoria> findPage(int pg, int linesPerpage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(pg, linesPerpage, Sort.Direction.valueOf(direction), orderBy);

        return rep.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objdto){
        return new Categoria(objdto.getId(), objdto.getNome());
    }

    private void updateData(Categoria newobj, Categoria obj){
        newobj.setNome(obj.getNome());
    }
}
