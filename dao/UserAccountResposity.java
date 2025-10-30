package com.example.loan.dao;

import com.example.loan.dao.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountResposity extends JpaRepository<UserAccount,Integer> {

    boolean existsUserAccountByName(String name);

    @Query(value = "select * from user_account where name=?1 and password=?2",nativeQuery = true)
    UserAccount login(String name,String password);

    UserAccount getUserAccountByName(String name);

}
