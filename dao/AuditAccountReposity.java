package com.example.loan.dao;

import com.example.loan.dao.entity.AuditAccount;
import com.example.loan.dao.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditAccountReposity extends JpaRepository<AuditAccount,Integer> {

    @Query(value = "select * from audit_account where name=?1 and password=?2",nativeQuery = true)
    AuditAccount login(String name, String password);

    AuditAccount getAuditAccountByName(String name);
}
