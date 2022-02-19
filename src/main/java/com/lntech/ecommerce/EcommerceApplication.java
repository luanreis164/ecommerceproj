package com.lntech.ecommerce;

import com.lntech.ecommerce.domain.Categorie;
import com.lntech.ecommerce.domain.Product;
import com.lntech.ecommerce.repositories.CategorieRepository;
import com.lntech.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	@Autowired
	private CategorieRepository categorieRepository;

	@Autowired
	private ProductRepository productRepository;


	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Categorie cat1 = new Categorie(null,"Computers");
		Categorie cat2 = new Categorie(null,"Books");
		Categorie cat3 = new Categorie(null,"Eletronics");

		List<Categorie> categories = new ArrayList<>();

		categorieRepository.saveAll(Arrays.asList(cat1,cat2,cat3));

		Product product1 = new Product(null,"Monitor 24p" , 300.00);
		Product product2 = new Product(null,"Mouse Gamer" , 139.99);
		Product product3 = new Product(null,"The Agile Manifesto" , 80.00);

		cat1.getProducts().addAll(Arrays.asList(product1,product2));
		cat2.getProducts().addAll(Arrays.asList(product3));
		cat3.getProducts().addAll(Arrays.asList(product1,product2));

		product1.getCategories().addAll(Arrays.asList(cat1,cat3));
		product2.getCategories().addAll(Arrays.asList(cat1,cat3));
		product3.getCategories().addAll(Arrays.asList(cat2));

		productRepository.saveAll(Arrays.asList(product1,product2,product3));
	}

}
