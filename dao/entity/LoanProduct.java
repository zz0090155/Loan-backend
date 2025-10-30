package com.example.loan.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "loan_product")
@NoArgsConstructor
@AllArgsConstructor
public class LoanProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(
            name = "intSnowflakeGenerator",
            strategy = "com.example.loan.utils.IntSnowflakeHibernateGenerator"
    )
    @GeneratedValue(generator = "intSnowflakeGenerator")
    private Integer id;

    private String name;

    @Column(name = "min_quota")
    private Integer minQuota;

    @Column(name = "max_quota")
    private Integer maxQuota;

    private float rate;

    @Column(name = "repay_method")
    private String repayMethod;

    @Column(name = "min_period")
    private Integer minPeriod;

    @Column(name = "max_period")
    private Integer maxPeriod;

    private Boolean status;
}
