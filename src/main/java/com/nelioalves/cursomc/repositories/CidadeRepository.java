package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Cidade;

/*
 * Integer -- IDENTIFICADOR DA CLASS CATEGORIA 
 * INTEFACE QUE IRÁ TER ACESSO AO DADOS E REALIZAR VARIAS TAREFAS 
 * DE ACESSO AOS DADOS COMO DELETA, BUSCAR, INSERIR DENTRE OUTRAS 
 * 
 * 
 * */

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	
	
}
