package com.example.loan.exception;

import com.example.loan.utils.ResponseResult;

public class ProductExistsException extends RuntimeException{
    private int code=400;
    private String msg="该产品已存在，不可再次上传";

    public <T> ResponseResult<T> error(T data){
        return ResponseResult.error(msg,code,data);
    }
}
