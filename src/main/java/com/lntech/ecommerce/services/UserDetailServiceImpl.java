package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.repositories.CustomerRepository;
import com.lntech.ecommerce.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = repo.findByEmail(email);
        if(customer == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(customer.getId(), customer.getEmail(), customer.getPassword(), customer.getProfiles());
    }
}
