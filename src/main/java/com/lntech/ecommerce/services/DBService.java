package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.*;
import com.lntech.ecommerce.domain.enums.Profile;
import com.lntech.ecommerce.domain.enums.StatePayment;
import com.lntech.ecommerce.domain.enums.TypeClient;
import com.lntech.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DBService {


    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ItemOrderedRepository itemOrderedRepository;

    @Autowired
    private BCryptPasswordEncoder pe;



    public void instantiateTestDatabase() throws ParseException {

        Categorie cat1 = new Categorie(null,"Computadores,Acessórios e Periféricos");
        Categorie cat2 = new Categorie(null,"Livros & Ebooks");
        Categorie cat3 = new Categorie(null,"Consoles & Acessórios");
        Categorie cat4 = new Categorie(null,"Escritório");
        Categorie cat5 = new Categorie(null,"Casa & Mais");
        Categorie cat6 = new Categorie(null,"Jardim");
        Categorie cat7 = new Categorie(null,"Games");

        List<Categorie> categories = new ArrayList<>();


        Product product1 = new Product(null,"Monitor 24p" , 300.00);
        Product product2 = new Product(null,"Mouse Gamer" , 139.99);
        Product product3 = new Product(null,"The Agile Manifesto" , 80.00);
        Product product4 = new Product(null,"Red Dead Redemption 2  " , 109.99);
        Product product5 = new Product(null,"Sekiro: Shadows Die Twice",115.00);
        Product product6 = new Product(null,"Cadeira de Escritorio Ergonomica",130.00);
        Product product7 = new Product(null,"Jogo americano de mesa",15.00);
        Product product8 = new Product(null,"Mousepad 120cmX60cm",60.00);
        Product product9 = new Product(null,"Rede de balançar verde",85.00);
        Product product10 = new Product(null,"Rede de balançar arco-íris",104.99);
        Product product11 = new Product(null,"EA33 Impressora/Escaneadora", 224.99);
        Product product12 = new Product(null,"Monitor 24p" , 300.00);
        Product product13 = new Product(null,"Mouse Gamer" , 139.99);
        Product product14 = new Product(null,"The Agile Manifesto" , 80.00);
        Product product15 = new Product(null,"Red Dead Redemption 2  " , 109.99);
        Product product16 = new Product(null,"Sekiro: Shadows Die Twice",115.00);
        Product product17 = new Product(null,"Office Desk Chair Ergonomic",130.00);
        Product product18 = new Product(null,"Dining Table Mats",15.00);
        Product product19 = new Product(null,"Mousepad 120cmX60cm",60.00);
        Product product20 = new Product(null,"Green Hammock",85.00);
        Product product21 = new Product(null,"Rainbow Hammock",104.99);
        Product product22 = new Product(null,"EA33 Printer/Scanner", 224.99);
        Product product23 = new Product(null,"Monitor 24p" , 300.00);
        Product product24 = new Product(null,"The Agile Manifesto" , 80.00);
        Product product25 = new Product(null,"Red Dead Redemption 2  " , 109.99);
        Product product26 = new Product(null,"Sekiro: Shadows Die Twice",115.00);
        Product product27 = new Product(null,"Office Desk Chair Ergonomic",130.00);
        Product product28 = new Product(null,"Dining Table Mats",15.00);
        Product product29 = new Product(null,"Mousepad 120cmX60cm",60.00);
        Product product30 = new Product(null,"Green Hammock",85.00);
        Product product31 = new Product(null,"Rainbow Hammock",104.99);
        Product product32 = new Product(null,"EA33 Printer/Scanner", 224.99);

        cat1.getProducts().addAll(Arrays.asList(product1,product2,product8,product11,product12,product13,product14,product15,product16,product17,product18,product19,product20,product21,product22,
                product23,product24,product25,product26,product27,product28,product29,product30,product31,product32));

        cat2.getProducts().addAll(Arrays.asList(product3));
        cat3.getProducts().addAll(Arrays.asList(product1,product2));
        cat4.getProducts().addAll(Arrays.asList(product6,product8,product11));
        cat5.getProducts().addAll(Arrays.asList(product9,product7));
        cat6.getProducts().addAll(Arrays.asList(product9,product10));
        cat7.getProducts().addAll(Arrays.asList(product4,product5));


        product1.getCategories().addAll(Arrays.asList(cat1,cat3));
        product2.getCategories().addAll(Arrays.asList(cat1,cat3));
        product3.getCategories().addAll(Arrays.asList(cat2));
        product4.getCategories().addAll(Arrays.asList(cat7));
        product5.getCategories().addAll(Arrays.asList(cat7));
        product6.getCategories().addAll(Arrays.asList(cat4));
        product7.getCategories().addAll(Arrays.asList(cat5));
        product8.getCategories().addAll(Arrays.asList(cat1,cat4));
        product9.getCategories().addAll(Arrays.asList(cat5,cat6));
        product10.getCategories().addAll(Arrays.asList(cat5,cat6));
        product11.getCategories().addAll(Arrays.asList(cat4,cat1));
        product12.getCategories().addAll(Arrays.asList(cat1));
        product13.getCategories().addAll(Arrays.asList(cat1));
        product14.getCategories().addAll(Arrays.asList(cat1));
        product15.getCategories().addAll(Arrays.asList(cat1));
        product16.getCategories().addAll(Arrays.asList(cat1));
        product17.getCategories().addAll(Arrays.asList(cat1));
        product18.getCategories().addAll(Arrays.asList(cat1));
        product19.getCategories().addAll(Arrays.asList(cat1));
        product20.getCategories().addAll(Arrays.asList(cat1));
        product21.getCategories().addAll(Arrays.asList(cat1));
        product22.getCategories().addAll(Arrays.asList(cat1));
        product23.getCategories().addAll(Arrays.asList(cat1));
        product24.getCategories().addAll(Arrays.asList(cat1));
        product25.getCategories().addAll(Arrays.asList(cat1));
        product26.getCategories().addAll(Arrays.asList(cat1));
        product27.getCategories().addAll(Arrays.asList(cat1));
        product28.getCategories().addAll(Arrays.asList(cat1));
        product29.getCategories().addAll(Arrays.asList(cat1));
        product30.getCategories().addAll(Arrays.asList(cat1));
        product31.getCategories().addAll(Arrays.asList(cat1));
        product32.getCategories().addAll(Arrays.asList(cat1));

        categorieRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));

        productRepository.saveAll(Arrays.asList(product1,product2,product3,product4,product5,product6,product7,product8,
                product9,product10,product11));

        productRepository.saveAll(Arrays.asList(product12,product13,product14,product15,product16,product17,product18,product19,product20,
                product21,product22,product23,product24,product25,product26,product27,product28,product29,product30,product31,product32 ));


        State state1 = new State(null,"São Paulo");
        State state2 = new State(null,"Rio De Janeiro");
        State state3 = new State(null,"Rio Grande do Sul");

        City city1 = new City(null,"Praia Grande", state1);
        City city2 = new City(null,"São Paulo", state1);
        City city3 = new City(null,"Rio de Janeiro", state2);
        City city4 = new City(null,"Porto Alegre", state3);

        state1.getCities().addAll(Arrays.asList(city1,city2));
        state2.getCities().addAll(Arrays.asList(city3));
        state3.getCities().addAll(Arrays.asList(city4));

        stateRepository.saveAll(Arrays.asList(state1, state2, state3));
        cityRepository.saveAll(Arrays.asList(city1,city2,city3,city4));

        Customer customer1 = new Customer(null,"Luan","luanreis2202@gmail.com","451.253.220-08", TypeClient.NATURALPERSON ,pe.encode("LuaN2202"));
        customer1.getTelephones().addAll(Arrays.asList("13 996735588","13 997564216"));
        customer1.addProfile(Profile.ADMIN);

        Customer customer2 = new Customer(null,"Saulo","saulo@gmail.com","939.305.270-06", TypeClient.NATURALPERSON ,pe.encode("123"));
        customer2.getTelephones().addAll(Arrays.asList("11 996221112","11 988777452"));

        Adress adress1 = new Adress(null,"Rua Josefina Bakhita","527","Casa 1","Vila Sonia","11722330", customer1,city1);
        Adress adress2 = new Adress(null,"Avenida Paulista","134","AP 22","Jardim Paulista","13574391", customer1,city2);
        Adress adress3 = new Adress(null,"Avenida São Jorge","211","AP 221","Capão Redondo","11233221", customer2,city2);

        customer1.getAdresses().addAll(Arrays.asList(adress1,adress2));
        customer2.getAdresses().addAll(Arrays.asList(adress3));

        customerRepository.saveAll(Arrays.asList(customer1,customer2));
        adressRepository.saveAll(Arrays.asList(adress1,adress2));

        //Mudando o formato de instanciação da data//
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Order order1 = new Order(null,sdf.parse("30/03/2022 12:30"), customer1,adress1);
        Order order2= new Order(null,sdf.parse("20/04/2022 11:15"), customer1,adress2);


        Payment payment1 = new CardPayment(null, StatePayment.SETTLED,order1,6);
        order1.setPayment(payment1);

        Payment payment2 = new BilletPayment(null, StatePayment.PENDING,order2 ,sdf.parse("20/03/2022 00:00"),null);
        order2.setPayment(payment2);

        customer1.getOrders().addAll(Arrays.asList(order1,order2));

        orderRepository.saveAll(Arrays.asList(order1,order2));
        paymentRepository.saveAll(Arrays.asList(payment1,payment2));

        ItemOrdered itemOrdered1 = new ItemOrdered(order1,product1,0.00,1,300.00);
        ItemOrdered itemOrdered2 = new ItemOrdered(order1,product3,0.00,2,80.00);
        ItemOrdered itemOrdered3 = new ItemOrdered(order2,product2,0.00,1,139.99);

        order1.getItems().addAll(Arrays.asList(itemOrdered1,itemOrdered2));
        order2.getItems().addAll(Arrays.asList(itemOrdered3));

        product1.getItens().addAll(Arrays.asList(itemOrdered1));
        product2.getItens().addAll(Arrays.asList(itemOrdered3));
        product3.getItens().addAll(Arrays.asList(itemOrdered2));

        itemOrderedRepository.saveAll(Arrays.asList(itemOrdered1,itemOrdered2,itemOrdered3));



    }


}
