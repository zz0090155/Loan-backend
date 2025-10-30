package com.example.loan.exception;

import com.example.loan.utils.ResponseResult;

public class UserAccountNotFoundException extends RuntimeException{
    private int code=404;
    private String msg="用户名或密码错误";

    public <T>ResponseResult<T> error(T data){
        return ResponseResult.error(msg,code,data);
    }
}
