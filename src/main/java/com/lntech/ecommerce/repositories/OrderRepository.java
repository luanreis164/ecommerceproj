package com.lntech.ecommerce.repositories;

import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Transactional(readOnly = true)
    Page<Order> findByCustomer(Customer customer, Pageable pageRequest);
}
