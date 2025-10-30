package com.example.loan.exception;

import com.example.loan.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class GlobalExcptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseResult<Void> globalException(){
//        return ResponseResult.error("服务器错误，请稍后重试",500,null);
//    }

    @ExceptionHandler(UserAccountExistsException.class)
    public ResponseResult<Void> userAccountExists(UserAccountExistsException e){
        return e.error(null);
    }

    @ExceptionHandler(UserAccountNotFoundException.class)
    public ResponseResult<Void> userAccountNotFound(UserAccountNotFoundException e){
        return e.error(null);
    }

    @ExceptionHandler(ProductExistsException.class)
    public ResponseResult<Void> productExists(ProductExistsException e){
        return e.error(null);
    }
}
