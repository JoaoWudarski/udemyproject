package com.cursosspringjpa.cursospringudemy.repository;

import com.cursosspringjpa.cursospringudemy.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
}
