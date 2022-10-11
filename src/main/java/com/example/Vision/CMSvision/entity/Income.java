package com.example.Vision.CMSvision.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int incomeId;
    @Temporal(TemporalType.DATE)
    private Date receivedDate;
    private double amount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "withdraw_withdrawId")
    private ClassFeeWithdraw withdraw;

}
