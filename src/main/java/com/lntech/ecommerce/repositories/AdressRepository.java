package com.lntech.ecommerce.repositories;

import com.lntech.ecommerce.domain.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress,Integer> {
}
