package com.lntech.ecommerce;

import com.lntech.ecommerce.domain.*;
import com.lntech.ecommerce.repositories.*;
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

	@Autowired
	private EstateRepository estateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CostumerRepository costumerRepository;

	@Autowired
	private AdressRepository adressRepository;


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

		Estate estate1 = new Estate(null,"São Paulo");
		Estate estate2 = new Estate(null,"Rio De Janeiro");
		Estate estate3 = new Estate(null,"Rio Grande do Sul");

		City city1 = new City(null,"Santos",estate1);
		City city2 = new City(null,"São Paulo",estate1);
		City city3 = new City(null,"Rio de Janeiro",estate2);
		City city4 = new City(null,"Porto Alegre",estate3);

		estate1.getCities().addAll(Arrays.asList(city1,city2));
		estate2.getCities().addAll(Arrays.asList(city3));
		estate3.getCities().addAll(Arrays.asList(city4));

		estateRepository.saveAll(Arrays.asList(estate1,estate2,estate3));
		cityRepository.saveAll(Arrays.asList(city1,city2,city3,city4));

		Costumer costumer1 = new Costumer(null,"Luan","luanreis2202@gmail.com","451.253.220-08",TypeClient.NATURALPERSON);
		costumer1.getTelephones().addAll(Arrays.asList("13 996735588","13 997564216"));

		Adress adress1 = new Adress(null,"Rua Josefina Bakhita","527","Casa 1","Vila Sonia","11722330",costumer1,city1);
		Adress adress2 = new Adress(null,"Avenida Paulista","134","AP 22","Jardim Paulista","13574391",costumer1,city2);

		costumer1.getAdresses().addAll(Arrays.asList(adress1,adress2));

		costumerRepository.saveAll(Arrays.asList(costumer1));
		adressRepository.saveAll(Arrays.asList(adress1,adress2));

	}

}
