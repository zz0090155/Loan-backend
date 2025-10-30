package com.example.loan.controller;

import com.example.loan.dao.entity.LoanProduct;
import com.example.loan.exception.ProductExistsException;
import com.example.loan.service.LoanProductService;
import com.example.loan.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoanProductController {

    @Autowired
    private LoanProductService loanProductService;

    @PostMapping("/adamin/product")
    public ResponseResult<Void> addProduct(@RequestBody LoanProduct loanProduct){
        if(loanProductService.addProduct(loanProduct)==0){
            throw new ProductExistsException();
        }else {
            return ResponseResult.success();
        }
    }

    @PutMapping("/adamin/product")
    public ResponseResult<Void> updateProduct(@RequestBody LoanProduct loanProduct){
        if(loanProductService.updateProduct(loanProduct)==0){
            return ResponseResult.error("产品不存在",404,null);
        }else {
            return ResponseResult.success();
        }
    }

    @DeleteMapping("/adamin/product")
    public ResponseResult<Void> deleteProduct(@RequestBody Map<String,String>map){
        String name=map.get("name");
        if(loanProductService.deleteProduct(name)==0){
            return ResponseResult.error("产品不存在",404,null);
        }else {
            return ResponseResult.success();
        }
    }

    @GetMapping("/product")
    public ResponseResult<PagedModel<LoanProduct>> getPage(@PageableDefault(size = 10,sort = "id")Pageable pageable,
                                                           String keyword){
        Page<LoanProduct> pages;
        if(keyword==null||keyword.isEmpty()){
            pages=loanProductService.getPage(pageable);
        }else {
            pages=loanProductService.getPageByKeyWord(keyword,pageable);
        }
        PagedModel<LoanProduct> pagedModel=new PagedModel<>(pages);
        if(pages==null){
            return ResponseResult.error("还没有你想要的产品",404,null);
        }
        return ResponseResult.success(200,"success",pagedModel);
    }
}
