package com.example.loan.service;

import com.example.loan.dao.UserQualificationReposity;
import com.example.loan.dao.entity.UserQualification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserQualificationService {

    @Autowired
    private UserQualificationReposity userQualificationReposity;

    public void addUserQualification(UserQualification userQualification,Integer id){
        userQualification.setId(id);
        userQualificationReposity.save(userQualification);
    }
}
