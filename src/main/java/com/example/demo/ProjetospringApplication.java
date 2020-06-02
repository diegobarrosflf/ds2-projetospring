package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Estado;
import com.example.demo.domain.Pagamento;
import com.example.demo.domain.PagamentoBoleto;
import com.example.demo.domain.PagamentoCredito;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.Produto;
import com.example.demo.domain.enums.StatusPagamento;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.CidadeRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EnderecoRepository;
import com.example.demo.repositories.EstadoRepository;
import com.example.demo.repositories.PagamentoRepository;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetospringApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetospringApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escrtório");
		Categoria cat3 = new Categoria(null, "Cozinha");

		Produto p1 = new Produto(null, "notebook_DELL", 1500.00);
		Produto p2 = new Produto(null, "impressora", 350.00);
		Produto p3 = new Produto(null, "cooktop", 600.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p3));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat3));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Ceará");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cd1 = new Cidade(null, "Fortaleza", est1);
		Cidade cd2 = new Cidade(null, "São Paulo", est2);
		Cidade cd3 = new Cidade(null, "Lorena", est2);

		est1.getCidades().add(cd1);
		est2.getCidades().addAll(Arrays.asList(cd2, cd3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cd1, cd2, cd3));
		
		Cliente cl1 = new Cliente(null, "Zezin da chica boa", "jose@gmail.com", "321656-54", TipoCliente.PESSOA_FISICA);
		Cliente cl2 = new Cliente(null, "Jucileudo Filho", "juju@gmail.com", "321598-74", TipoCliente.PESSOA_FISICA);
		
		
		Endereco end1 = new Endereco(null, "Rua 3 de maio", "235", "casa 3A", "Bela Vista", "32165485", cl1, cd1);
		Endereco end2 = new Endereco(null, "Rua Silva Bueno", "222", "sal 1069", "Ipiranga", "32154698", cl2, cd2);
				
		cl1.getEnderecos().addAll(Arrays.asList(end1));
		cl1.getTelefones().addAll(Arrays.asList("3219-6548","6548-9871"));
		
		cl2.getEnderecos().add(end2);
		cl2.getTelefones().addAll(Arrays.asList("6541-3219","9764-3164"));
		
		clienteRepository.saveAll(Arrays.asList(cl1,cl2));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("29/05/2020 20:15"), cl1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("03/06/2020 22:15"), cl2, end2);
		
		Pagamento pg1 = new PagamentoCredito(null, StatusPagamento.QUITADO, ped1, 6);
		Pagamento pg2 = new PagamentoBoleto(null, StatusPagamento.PENDENTE, ped2, sdf.parse("03/06/2020 20:41"), null);
		
		ped1.setPagamento(pg1);
		ped2.setPagamento(pg2);
		
		cl1.getPedidos().add(ped1);
		cl2.getPedidos().add(ped2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pg1,pg2));
		

	}

}
