package com.example.loan.controller;

import com.example.loan.dao.entity.AuditAccount;
import com.example.loan.exception.UserAccountNotFoundException;
import com.example.loan.service.AuditAccountService;
import com.example.loan.service.RedisService;
import com.example.loan.utils.JwtUtils;
import com.example.loan.utils.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/adamin")
public class AuditAccountController {

    @Autowired
    private AuditAccountService auditAccountService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/login")
    public ResponseResult<Void> login(@RequestBody AuditAccount auditAccount, HttpServletRequest request){
        if(auditAccountService.login(auditAccount)==0){
            throw new UserAccountNotFoundException();
        }else {
            HttpSession session=request.getSession();
            session.setAttribute("name",auditAccount.getName());
            String token= JwtUtils.createToken(auditAccount.getPersonality(),auditAccount.getName());
            redisService.set(auditAccount.getName()+":token",token,24, TimeUnit.HOURS);
            return ResponseResult.success();
        }
    }

    @PutMapping("/modify")
    public ResponseResult<Void> modify(@RequestBody AuditAccount auditAccount,HttpServletRequest request){
        HttpSession session=request.getSession();
        String name=(String) session.getAttribute("name");
        AuditAccount auditAccount1=auditAccountService.modify(auditAccount,name);

        session.setAttribute("name",auditAccount1.getName());
        redisService.delete(name+":token");
        String token=JwtUtils.createToken(auditAccount1.getPersonality(),auditAccount1.getName());
        redisService.set(auditAccount1.getName()+":token",token,24,TimeUnit.HOURS);
        return ResponseResult.success();
    }

    @PostMapping("/register")
    public ResponseResult<Void> register(@RequestBody AuditAccount auditAccount){
        auditAccountService.register(auditAccount);
        return ResponseResult.success();
    }
}
