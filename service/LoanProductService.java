package com.example.loan.service;

import com.example.loan.dao.LoanProductReposity;
import com.example.loan.dao.entity.LoanProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LoanProductService {

    @Autowired
    private LoanProductReposity loanProductReposity;

    @CacheEvict(cacheNames = "LoanProductCache",allEntries = true)
    public int addProduct(LoanProduct loanProduct){
            if(loanProductReposity.existsByName(loanProduct.getName())){
                return 0;
            }else {
                loanProductReposity.save(loanProduct);
                return loanProduct.getId();
            }
    }

    @CacheEvict(cacheNames = "LoanProductCache",allEntries = true)
    public int updateProduct(LoanProduct loanProduct){
        if(loanProductReposity.existsByName(loanProduct.getName())){
            LoanProduct loanProduct1=loanProductReposity.getByName(loanProduct.getName());
            loanProduct.setId(loanProduct1.getId());
            loanProductReposity.save(loanProduct);
            return 1;
        }else {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "LoanProductCache",allEntries = true)
    public int deleteProduct(String name){
        if(loanProductReposity.existsByName(name)){
            loanProductReposity.deleteByName(name);
            return 1;
        }else {
            return 0;
        }
    }

    @Cacheable(cacheNames = "LoanProductCache",key = "#pageable.pageNumber")
    public Page<LoanProduct> getPage(Pageable pageable){
        return loanProductReposity.findAll(pageable);
    }

    public Page<LoanProduct> getPageByKeyWord(String keyword,Pageable pageable){
        return loanProductReposity.findByKeyword(keyword,pageable);
    }
}
