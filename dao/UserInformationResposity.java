package com.example.loan.dao;

import com.example.loan.dao.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationResposity extends JpaRepository<UserInformation,Integer> {

    UserInformation getUserInformationById(Integer id);

    Boolean existsUserInformationById(Integer id);
}
