package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.example.demo.domain.enums.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	
	public PagamentoBoleto() {
		
	}

	public PagamentoBoleto(Integer id, StatusPagamento status,
			Pedido pedido, Date vencimento, Date pagamento) {
		
		super(id, status, pedido);
		this.dataPagamento = pagamento;
		this.dataVencimento = vencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	
	
	
}
