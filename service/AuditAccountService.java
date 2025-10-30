package com.example.loan.service;

import com.example.loan.dao.AuditAccountReposity;
import com.example.loan.dao.entity.AuditAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AuditAccountService {

    @Autowired
    private AuditAccountReposity auditAccountReposity;

    public int login(AuditAccount auditAccount){
        AuditAccount auditAccount1=auditAccountReposity.login(auditAccount.getName(),auditAccount.getPassword());
        if(auditAccount1==null){
            return 0;
        }else {
            return 1;
        }
    }

    public AuditAccount modify(AuditAccount auditAccount,String name){
        AuditAccount auditAccount1=auditAccountReposity.getAuditAccountByName(name);
        auditAccount1.setName(auditAccount.getName());
        auditAccount1.setPassword(auditAccount.getPassword());
        auditAccountReposity.save(auditAccount1);
        return auditAccount1;
    }

    public void register(AuditAccount auditAccount){
        auditAccount.setPersonality("audit_worker");
        auditAccountReposity.save(auditAccount);
    }
}
