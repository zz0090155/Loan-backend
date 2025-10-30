package com.example.loan.service;

import com.example.loan.dao.UserAccountResposity;
import com.example.loan.dao.UserInformationResposity;
import com.example.loan.dao.entity.UserAccount;
import com.example.loan.dao.entity.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserInformationService {

    @Autowired
    private UserInformationResposity userInformationResposity;

    @Autowired
    private UserAccountResposity userAccountResposity;

    public void addInformation(UserInformation userInformation,Integer id){
        userInformation.setId(id);
        userInformation.setCreditScore(100);
        userInformationResposity.save(userInformation);
    }

    public int updateInformation(UserInformation userInformation,Integer id){
        UserInformation userInformation1=userInformationResposity.getUserInformationById(id);
        if(userInformation1==null){
            return 0;
        }

        userInformation.setId(userInformation1.getId());
        userInformation.setCreditScore(userInformation1.getCreditScore());
        userInformationResposity.save(userInformation);
        return 1;
    }

    public int getScore(String name){
        UserAccount userAccount=userAccountResposity.getUserAccountByName(name);
        if(userInformationResposity.existsUserInformationById(userAccount.getId())){
            UserInformation userInformation=userInformationResposity.getUserInformationById(userAccount.getId());
            return userInformation.getCreditScore();
        }else {
            return 100;
        }
    }
}
