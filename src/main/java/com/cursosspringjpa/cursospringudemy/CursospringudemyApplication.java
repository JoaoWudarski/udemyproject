package com.cursosspringjpa.cursospringudemy;

import java.util.Arrays;

import com.cursosspringjpa.cursospringudemy.model.Categoria;
import com.cursosspringjpa.cursospringudemy.model.Cidade;
import com.cursosspringjpa.cursospringudemy.model.Estado;
import com.cursosspringjpa.cursospringudemy.model.Produto;
import com.cursosspringjpa.cursospringudemy.repository.CategoriaRepository;
import com.cursosspringjpa.cursospringudemy.repository.CidadeRepository;
import com.cursosspringjpa.cursospringudemy.repository.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursospringudemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Compultador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		repCat.saveAll(Arrays.asList(cat1, cat2));
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
	}

}
