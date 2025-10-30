package com.example.loan.dao;

import com.example.loan.dao.entity.LoanProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanProductReposity extends JpaRepository<LoanProduct,Integer> {

    Boolean existsByName(String name);

    LoanProduct getByName(String name);

    Void deleteByName(String name);

    @Query(value = "select * from  loan_product " +
            "where name like concat('%',?1,'%') or repay_method like concat('%',?1,'%')",nativeQuery = true)
    Page<LoanProduct> findByKeyword(String keyword, Pageable pageable);
}
