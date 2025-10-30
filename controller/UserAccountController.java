package com.example.loan.controller;

import com.example.loan.dao.entity.UserAccount;
import com.example.loan.exception.UserAccountExistsException;
import com.example.loan.exception.UserAccountNotFoundException;
import com.example.loan.service.RedisService;
import com.example.loan.service.UserAccountService;
import com.example.loan.utils.JwtUtils;
import com.example.loan.utils.ResponseResult;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/users")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/register")
    public ResponseResult<Void> register(@RequestBody UserAccount userAccount){
        if(userAccountService.existsUserAccount(userAccount)){
            throw new UserAccountExistsException();
        }
        userAccount.setPersonality("user");
        userAccountService.register(userAccount);
        return ResponseResult.success();
    }

    @PostMapping("/login")
    public ResponseResult<Void> login(@RequestBody UserAccount userAccount, HttpServletRequest request) throws UnsupportedEncodingException {
        int flag=userAccountService.login(userAccount);
        if(flag!=0){
            String key=userAccount.getName()+":token";
            String token=JwtUtils.createToken(userAccount.getPersonality(),userAccount.getName());
            redisService.set(key,token,24, TimeUnit.HOURS);
            HttpSession session=request.getSession();
            session.setAttribute("name",userAccount.getName());
            return ResponseResult.success();
        }else{
             throw new UserAccountNotFoundException();
        }
    }

    @PutMapping("/modify")
    public ResponseResult<Void> modify(@RequestBody UserAccount userAccount, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        String name=null;
        HttpSession session=request.getSession();
        name=(String) session.getAttribute("name");

        UserAccount userAccount1=userAccountService.getUserAccountByName(name);
        redisService.delete(userAccount1.getName()+":token");
        userAccount1.setName(userAccount.getName());
        userAccount1.setPassword(userAccount.getPassword());
        userAccountService.register(userAccount1);

        String token=JwtUtils.createToken(userAccount1.getPersonality(),userAccount1.getName());
        String key=userAccount1.getName()+":token";
        redisService.set(key,token,24, TimeUnit.HOURS);

        session.setAttribute("name",userAccount1.getName());
        return ResponseResult.success();
    }
}
