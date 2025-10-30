package com.example.loan.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_information")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation {

    @Id
    private Integer id;

    private String name;
    private String nationality;

    @Column(name = "id_card_number")
    private String idCardNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;
    private String adress;

    @Column(name = "add_date",updatable = false)
    @CreatedDate
    private LocalDateTime addDate;

    @Column(name = "update_date")
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Column(name = "bank_card_id")
    private String bankCardId;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_card_status")
    private String bankCardStatus;

    @Column(name = "credit_score")
    private Integer creditScore;
}
