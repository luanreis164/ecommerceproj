package com.lntech.ecommerce.repositories;

import com.lntech.ecommerce.domain.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateRepository extends JpaRepository<Estate,Integer> {
}
