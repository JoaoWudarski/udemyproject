package com.cursosspringjpa.cursospringudemy.repository;

import com.cursosspringjpa.cursospringudemy.model.ItemPedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    
}
