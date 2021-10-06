package com.example.financys;

import com.example.financys.category.Category;
import com.example.financys.controller.CategoryController;
import com.example.financys.controller.EntryController;
import com.example.financys.entry.Entry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinancysApplication {

	public static void main(String[] args) {

		SpringApplication.run(FinancysApplication.class, args);

		/*
		* A parte manual como foi pedida no desafio está comentada abaixo, usei postman em seguida para testar a api rest e logo em
		* seguida instalei a depenência do swagger no pom.xml, criei a classe de configuração do swagger,
		* classe que busca api e os caminhos, e testei, tudo ok.
		* */


		//Desafio 2

//		Category category = new Category(4, "Salário", "Recebimento de salário");
//		Category category2 = new Category();
//
//		category2.setId(4);
//		category2.setName("Salário");
//		category2.setDescription("Recebimento de salário");
//
//		Entry entry = new Entry(3, "Salário na empresa X", "Adiantamento quinzenal", "receita",
//				"4405.49", "29/09/21", true, 4);
//
//		Entry entry2 = new Entry();
//
//		entry2.setId(3);
//		entry2.setName("Salário na empresa X");
//		entry2.setDescription("Adiantamento quinzenal");
//		entry2.setType("receita");
//		entry2.setAmount("4405.49");
//		entry2.setDate("15/09/21");
//		entry2.isPaid(true);
//		entry2.setCategoryId(4);
//
//		//Desafio 3 logo abaixo
//
//		CategoryController categoryController = new CategoryController();
//
//		categoryController.create(category);
//		categoryController.create(category2);
//
//		EntryController entryController = new EntryController();
//
//		entryController.create(entry);
//		entryController.create(entry2);
//
//		System.out.println(entryController.read());

	}

}
