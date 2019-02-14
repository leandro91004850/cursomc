package com.nelioalves.cursomc;

import java.text.SimpleDateFormat;
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
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		
		Cliente cli2 = new Cliente(null, "joão da Silva", "joda487@gmail.com", "05671622150", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("994052983", "984214522"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "apto 309", "Jardim", "72884500", cli1, c1); //cli1, c1: REALIZANDO AS ASSOCIAÇÕES
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "sala 109", "Centro", "23584500", cli2, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1));// REALIZANDO O RECONHECIMENTO DOS ENDEREÇOS 
		cli1.getEnderecos().addAll(Arrays.asList(end2));
		
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:40"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 01:40"), cli1, end2);
		
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagamento2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2)); // PEDIDOS DO CLIENTE 1
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		
		
	}
	
	
}
