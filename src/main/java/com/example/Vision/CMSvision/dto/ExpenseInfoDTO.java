package com.example.Vision.CMSvision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseInfoDTO {
    private int expenseInfoId;
    private String code;
    private int status;
    private String description;
    private String expenseTitle;
}
