package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Adress;
import com.lntech.ecommerce.domain.City;
import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.domain.enums.Profile;
import com.lntech.ecommerce.domain.enums.TypeClient;
import com.lntech.ecommerce.dto.CustomerDTO;
import com.lntech.ecommerce.dto.NewCustomerDTO;
import com.lntech.ecommerce.repositories.AdressRepository;
import com.lntech.ecommerce.repositories.CustomerRepository;
import com.lntech.ecommerce.security.UserSS;
import com.lntech.ecommerce.services.exceptions.AuthorizationException;
import com.lntech.ecommerce.services.exceptions.DataIntegrityException;
import com.lntech.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.customer.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;

    public Customer find(Integer id){
        UserSS user = UserService.authenticated();
        if(user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Acesso negado!");
        }


        Optional<Customer> obj = repo.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Cliente não encontrado!Id:" + id + ",Tipo: " + Customer.class.getName()) );
    }

    public List<Customer> findAll(){
        List<Customer> obj = repo.findAll();
        return obj;
    }
    public Customer update(Customer obj){
        Customer newObj = find(obj.getId());
        updateData(newObj,obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir porque há pedidos! ");
        }

    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }

    public Customer fromDTO(CustomerDTO objDto){
        return new Customer(objDto.getId(), objDto.getName(), objDto.getEmail(),null,null,null);
    }

    public Customer fromDTO(NewCustomerDTO objDto){
        Customer customer = new Customer(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), TypeClient.toEnum(objDto.getType()), pe.encode(objDto.getPassword()));
        City city = new City(objDto.getCityId(), null,null);
        Adress adress = new Adress(null,objDto.getAddress(), objDto.getNumber(), objDto.getComplement(), objDto.getNeighborhood(), objDto.getPostalCode(), customer,city);

        customer.getAdresses().add(adress);
        customer.getTelephones().add(objDto.getTelephone1());
        if(objDto.getTelephone2() != null){
            customer.getTelephones().add(objDto.getTelephone2());
        }
        if (objDto.getTelephone3() != null){
            customer.getTelephones().add(objDto.getTelephone3());
        }
        return customer;
    }


    private void updateData(Customer newObj, Customer obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    @Transactional
    public Customer insert(Customer obj){
    obj.setId(null);
    obj = repo.save(obj);
    adressRepository.saveAll(obj.getAdresses());
    return obj;
    }

    public URI uploadProfilePicture(MultipartFile multipartFile){

        UserSS user = UserService.authenticated();
        if(user == null){
            throw new AuthorizationException("Acesso negado");
        }
        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage,size);

        String fileName = prefix + user.getId() + ".jpg";

        return s3Service.uploadFile(imageService.getInputStream(jpgImage,"jpg"),fileName,"image" );

    }

    public Customer findByEmail(String email){

        UserSS user = UserService.authenticated();
        if(user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())){
            throw new AuthorizationException("Acesso negado");
        }
        Customer customer = repo.findByEmail(email);
        if(customer == null){
            throw new ObjectNotFoundException("Cliente não encontrado! Email: " + user.getUsername() + ", Tipo: " + Customer.class.getName());
        }
        return customer;

    }


}
