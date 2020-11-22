package com.cursosspringjpa.cursospringudemy.repository;

import com.cursosspringjpa.cursospringudemy.model.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    
}
