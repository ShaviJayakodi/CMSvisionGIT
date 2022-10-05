package com.example.Vision.CMSvision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
    private int expenseId;
    private int expenseInfoId;
    private int status;
    private String description;
    private double value;
    private Date date;
}
