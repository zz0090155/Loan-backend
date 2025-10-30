package com.example.loan.dao;

import com.example.loan.dao.entity.UserQualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQualificationReposity extends JpaRepository<UserQualification,Integer> {
}
