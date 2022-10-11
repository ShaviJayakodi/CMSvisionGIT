package com.example.Vision.CMSvision.dto;

import com.example.Vision.CMSvision.entity.ClassFeeWithdraw;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDTO {

    private int incomeId;
    private Date receivedDate;
    private double amount;
    private int withdrawId;
}
