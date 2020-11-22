package com.cursosspringjpa.cursospringudemy.model;

import javax.persistence.Entity;

import com.cursosspringjpa.cursospringudemy.model.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento {
    private static final long serialVersionUID = 1L;
    
    private Integer numparcelas;

    public PagamentoCartao() {
    }

    public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numparcelas) {
        super(id, estado, pedido);
        this.numparcelas = numparcelas;
    }

    public Integer getNumparcelas() {
        return numparcelas;
    }

    public void setNumparcelas(Integer numparcelas) {
        this.numparcelas = numparcelas;
    }

    
}
