package com.example.loan.exception;

import com.example.loan.utils.ResponseResult;

public class UserAccountExistsException extends RuntimeException{
    private int code=400;
    private String msg="该用户已经注册，不可再次注册";

    public <T> ResponseResult<T> error(T data){
        return ResponseResult.error(msg,code,data);
    }
}
