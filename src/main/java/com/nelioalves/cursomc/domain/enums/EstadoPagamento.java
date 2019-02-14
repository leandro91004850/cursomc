package com.nelioalves.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE (1, "Pendente"),	
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
		
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
		
	}
	
	// UM OBJETO STATIC PODE SER EXECUTADO MESMO SEM SER EXTANSIADO 
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		// IRAR VARRER OS TIPOS DE CLIENTE SE FOR ENCONTRADO VAI RETORNA OS CLIENTES 
		for(EstadoPagamento x : EstadoPagamento.values()){
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: "+cod);
	}
	
}
