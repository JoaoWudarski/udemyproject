package com.cursosspringjpa.cursospringudemy.service;

import java.util.List;
import java.util.Optional;

import com.cursosspringjpa.cursospringudemy.dto.ClienteDTO;
import com.cursosspringjpa.cursospringudemy.model.Cliente;
import com.cursosspringjpa.cursospringudemy.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);

        return rep.save(newObj);
    }

    public void delete(Integer id){
        try {
            rep.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Não é possível excluir cliente com entidades relacionadas"));
        }
        
    }

    public List<Cliente> findAll(){
        return rep.findAll();
    }

    public Page<Cliente> findPage(int pg, int linesPerpage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(pg, linesPerpage, Sort.Direction.valueOf(direction), orderBy);

        return rep.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objdto){
        return new Cliente(objdto.getId(), objdto.getNome(), objdto.getEmail(), null, null);
    }

    private void updateData(Cliente newobj, Cliente obj){
        newobj.setNome(obj.getNome());
        newobj.setEmail(obj.getEmail());
    }
}
