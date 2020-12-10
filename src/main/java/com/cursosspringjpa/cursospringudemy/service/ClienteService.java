package com.cursosspringjpa.cursospringudemy.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.cursosspringjpa.cursospringudemy.dto.ClienteDTO;
import com.cursosspringjpa.cursospringudemy.dto.ClienteNewDTO;
import com.cursosspringjpa.cursospringudemy.model.Cidade;
import com.cursosspringjpa.cursospringudemy.model.Cliente;
import com.cursosspringjpa.cursospringudemy.model.Endereco;
import com.cursosspringjpa.cursospringudemy.model.enums.TipoCliente;
import com.cursosspringjpa.cursospringudemy.repository.ClienteRepository;
import com.cursosspringjpa.cursospringudemy.repository.EnderecoRepository;

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

    @Autowired
    private EnderecoRepository repEnd;

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

    public Cliente fromDTO(ClienteNewDTO objdto){
        Cliente cli = new Cliente(null, objdto.getNome(), objdto.getEmail(), objdto.getCpfOrcnpj(), TipoCliente.toEnum(objdto.getTipo()));
        Cidade cid = new Cidade(objdto.getCidadeID(), null, null);
        Endereco end = new Endereco(null, objdto.getLogradouro(), objdto.getNumero(), objdto.getComplemento(), objdto.getBairro(), objdto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objdto.getTel1());
        if(objdto.getTel2() != null)
            cli.getTelefones().add(objdto.getTel2());
        if(objdto.getTel3() != null)
            cli.getTelefones().add(objdto.getTel3());
        return cli;    
    }

    private void updateData(Cliente newobj, Cliente obj){
        newobj.setNome(obj.getNome());
        newobj.setEmail(obj.getEmail());
    }

    @Transactional
    public Cliente insert(Cliente obj){
        obj.setId(null);
        obj = rep.save(obj);
        repEnd.saveAll(obj.getEnderecos());

        return obj;
    }
}
