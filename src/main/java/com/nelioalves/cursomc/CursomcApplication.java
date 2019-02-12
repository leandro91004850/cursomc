package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
//https://github.com/acenelio/springboot2-ionic-backend/commits/master?after=b4c46cea84b489b7199b8f572e100f078b275ab9+34
import com.nelioalves.cursomc.repositories.ProdutoRepository;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new  Categoria(null, "Informática");
		Categoria cat2 = new  Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3)); // REALIZANDO A ASSOCIAÇÃO ENTRE CATEGORIAS E PRODUTOS
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1)); // REALIZANDO A ASSOCIAÇÃO ENTRE CATEGORIAS E PRODUTOS
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2, p3));
		
		Estado est1 = new Estado(null, "Minas gerais");
		Estado est2 = new Estado(null, "São paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São paulo", est2); 
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1)); // SALVANDO AS PERSISTENCIAS DE DADOS NA BASE DE DADOS 
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "maria silva", "maria789@gmail.com", "05671622150", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("994052033", "987214522"));// INSERINDO DADOS A TABLE TELEFONE
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "apto 309", "Jardim", "72884500",cli1, c1); //cli1, c1: REALIZANDO AS ASSOCIAÇÕES
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "sala 109", "Centro", "23584500",cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));// REALIZANDO O RECONHECIMENTO DOS ENDEREÇOS 
		
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
	}
	
	
}
