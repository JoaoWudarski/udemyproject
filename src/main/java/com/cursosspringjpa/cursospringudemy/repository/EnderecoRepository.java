package com.cursosspringjpa.cursospringudemy.repository;

import com.cursosspringjpa.cursospringudemy.model.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
    
}
