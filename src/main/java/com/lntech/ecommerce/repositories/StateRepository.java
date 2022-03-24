package com.lntech.ecommerce.repositories;

import com.lntech.ecommerce.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State,Integer> {

    @Transactional
    List<State> findAllByOrderByName();

}
