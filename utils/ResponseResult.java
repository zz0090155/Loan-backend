package com.example.loan.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    private String msg;
    private int code;
    private T data;

    public static <T> ResponseResult<T> success(int code,String msg,T data){
        return new ResponseResult<T>(msg,code,data);
    }

    public static <T> ResponseResult<T> success(){
        return new ResponseResult<T>("success",200,null);
    }

    public static <T> ResponseResult<T> error(String msg,int code,T data){
        return new ResponseResult<T>(msg,code,data);
    }

    public static <T> ResponseResult<T> error(){
        return new ResponseResult<T>("error",400,null);
    }
}
