package com.cursosspringjpa.cursospringudemy.model;

import java.util.Date;

import javax.persistence.Entity;

import com.cursosspringjpa.cursospringudemy.model.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date datavencimento;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date datapagamento;

    public PagamentoBoleto() {
    }

    public PagamentoBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date datavencimento, Date datapagamento) {
        super(id, estado, pedido);
        this.datavencimento = datavencimento;
        this.datapagamento = datapagamento;
    }

    public Date getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    
}
