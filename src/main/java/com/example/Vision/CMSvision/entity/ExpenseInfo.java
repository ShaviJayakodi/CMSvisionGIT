package com.example.Vision.CMSvision.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExpenseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseInfoId;
    private String code;
    private int status;
    private String description;
    private String expenseTitle;
}
