package com.lntech.ecommerce.repositories;

import com.lntech.ecommerce.domain.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer,Integer> {
}
