package com.nelioalves.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

// SUBCLASSE DE PAGAMENTO, VAI POSSUIR TUDO QUE PAGAMENTO TEM 
@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L; // SOMENTE SERIAL NAS SUBCLASSES 
	
	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoComBoleto() {
		
	}


	// CONSTRUTOR BASEADO COMO SUBCLASSE DE PAGAMENTO 
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
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
