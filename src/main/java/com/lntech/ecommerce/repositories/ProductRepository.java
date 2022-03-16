package com.lntech.ecommerce.repositories;

import com.lntech.ecommerce.domain.Categorie;
import com.lntech.ecommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    //@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories ")
    @Transactional
    Page<Product> findDistinctByNameContainingAndCategoriesIn(String name,List<Categorie> categories, Pageable pageRequest);
}
