package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.repositories.CustomerRepository;
import com.lntech.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();


    public void sendNewPassword(String email){
        Customer customer = customerRepository.findByEmail(email);
        if(customer == null){
            throw new ObjectNotFoundException("Email não encontrado!");
        }

        String newPass = newPassword();
        customer.setPassword(pe.encode(newPass));

        customerRepository.save(customer);
        emailService.sendNewPasswordEmail(customer,newPass);
    }

    private String newPassword(){
        char[] vet = new char[10];
        for(int i=0;i<10;i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if(opt == 0){
            return (char) (random.nextInt(10) + 48 );
        }
        else if(opt == 1){
            return (char) (random.nextInt(26)+ 65);
        }
        else {
            return (char) (random.nextInt(26) + 97);
        }
    }


}
