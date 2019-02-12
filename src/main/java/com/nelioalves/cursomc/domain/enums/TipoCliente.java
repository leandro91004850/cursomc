package com.nelioalves.cursomc.domain.enums;

public enum TipoCliente {

	/* 
	 * CONTROLE MANUAL DA ENUMERAÇÃO ID ATRIBUIDO A CADA ELEMENTO INSERIDO
	 * 
	 * */

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
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
	
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		// IRAR VARRER OS TIPOS DE CLIENTE SE FOR ENCONTRADO VAI RETORNA OS CLIENTES 
		for(TipoCliente x : TipoCliente.values()){
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: "+cod);
	}
	
	
}











