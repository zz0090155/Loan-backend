package com.example.loan.service;

import com.example.loan.dao.entity.UserAccount;
import com.example.loan.dao.UserAccountResposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountResposity userAccountResposity;

    public void register(UserAccount userAccount){
        userAccountResposity.save(userAccount);
    }

    public boolean existsUserAccount(UserAccount userAccount){
        return userAccountResposity.existsUserAccountByName(userAccount.getName());
    }

    public int login(UserAccount userAccount){
        UserAccount userAccount1=userAccountResposity.login(userAccount.getName(),userAccount.getPassword());
        if(userAccount1!=null){
            return userAccount1.getId();
        }else {
            return 0;
        }
    }

    public UserAccount getUserAccountByName(String name){
        return userAccountResposity.getUserAccountByName(name);
    }
}
