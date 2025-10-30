package com.example.loan.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table(name = "audit_account")
@NoArgsConstructor
@AllArgsConstructor
public class AuditAccount {

    @Id
    @GenericGenerator(
            name = "intSnowflakeGenerator",
            strategy = "com.example.loan.utils.IntSnowflakeHibernateGenerator"
    )
    @GeneratedValue(generator = "intSnowflakeGenerator")
    private Integer id;

    private String name;
    private String personality;
    private String password;

}
