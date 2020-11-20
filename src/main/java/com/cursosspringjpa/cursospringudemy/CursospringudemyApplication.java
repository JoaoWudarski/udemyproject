package com.cursosspringjpa.cursospringudemy;

import java.util.Arrays;

import com.cursosspringjpa.cursospringudemy.model.Categoria;
import com.cursosspringjpa.cursospringudemy.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursospringudemyApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository rep;

	public static void main(String[] args) {
		SpringApplication.run(CursospringudemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		rep.saveAll(Arrays.asList(cat1, cat2));
	}

}
