package com.cursosspringjpa.cursospringudemy;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.cursosspringjpa.cursospringudemy.model.Categoria;
import com.cursosspringjpa.cursospringudemy.model.Cidade;
import com.cursosspringjpa.cursospringudemy.model.Cliente;
import com.cursosspringjpa.cursospringudemy.model.Endereco;
import com.cursosspringjpa.cursospringudemy.model.Estado;
import com.cursosspringjpa.cursospringudemy.model.ItemPedido;
import com.cursosspringjpa.cursospringudemy.model.Pagamento;
import com.cursosspringjpa.cursospringudemy.model.PagamentoBoleto;
import com.cursosspringjpa.cursospringudemy.model.PagamentoCartao;
import com.cursosspringjpa.cursospringudemy.model.Pedido;
import com.cursosspringjpa.cursospringudemy.model.Produto;
import com.cursosspringjpa.cursospringudemy.model.enums.EstadoPagamento;
import com.cursosspringjpa.cursospringudemy.model.enums.TipoCliente;
import com.cursosspringjpa.cursospringudemy.repository.CategoriaRepository;
import com.cursosspringjpa.cursospringudemy.repository.CidadeRepository;
import com.cursosspringjpa.cursospringudemy.repository.ClienteRepository;
import com.cursosspringjpa.cursospringudemy.repository.EnderecoRepository;
import com.cursosspringjpa.cursospringudemy.repository.EstadoRepository;
import com.cursosspringjpa.cursospringudemy.repository.ItemPedidoRepository;
import com.cursosspringjpa.cursospringudemy.repository.PagamentoRepository;
import com.cursosspringjpa.cursospringudemy.repository.PedidoRepository;
import com.cursosspringjpa.cursospringudemy.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursospringudemyApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository repCat;

	@Autowired
	private ProdutoRepository repPro;

	@Autowired
	private EstadoRepository repEst;

	@Autowired
	private CidadeRepository repCid;

	@Autowired
	private ClienteRepository repCli;

	@Autowired
	private EnderecoRepository repEnd;

	@Autowired
	private PagamentoRepository repPag;

	@Autowired
	private PedidoRepository repPed;

	@Autowired
	private ItemPedidoRepository repIte;

	public static void main(String[] args) {
		SpringApplication.run(CursospringudemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama e Banho");
		Categoria cat4 = new Categoria(null, "Eletrônico");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Compultador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		repCat.saveAll(Arrays.asList(cat1, cat2 , cat3, cat4, cat5, cat6, cat7));
		repPro.saveAll(Arrays.asList(p1, p2, p3));


		Estado est1 = new Estado(null, "Mina Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		repEst.saveAll(Arrays.asList(est1, est2));
		repCid.saveAll(Arrays.asList(c1, c2, c3));


		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("(27)3632-3234", "(11)3284-7530"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", null, "Parque das arvores", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		repCli.saveAll(Arrays.asList(cli1));
		repEnd.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("22/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		repPed.saveAll(Arrays.asList(ped1, ped2));
		repPag.saveAll(Arrays.asList(pagto1, pagto2));


		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.0);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		repIte.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
