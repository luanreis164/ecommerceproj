package com.lntech.ecommerce.repositories;

import com.lntech.ecommerce.domain.ItemOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderedRepository extends JpaRepository<ItemOrdered,Integer> {
}
