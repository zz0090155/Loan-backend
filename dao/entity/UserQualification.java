package com.example.loan.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_qualification")
@NoArgsConstructor
@AllArgsConstructor
public class UserQualification {

    @Id
    private Integer id;

    private String name;

    @Column(name = "marriage_status")
    private Boolean marriageStatus;

    @Column(name = "education_background")
    private String educationBackground;

    private String profession;
    private Integer income;

    @Column(name = "social_insurance_status")
    private Boolean socialInsuranceStatus;

    @Column(name = "credit_status")
    private Boolean creditStatus;
}
