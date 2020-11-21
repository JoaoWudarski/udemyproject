package com.cursosspringjpa.cursospringudemy.repository;

import com.cursosspringjpa.cursospringudemy.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
