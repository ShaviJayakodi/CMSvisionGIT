package com.example.Vision.CMSvision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassFeeWithdrawDTO {
    private int withdrawId;
    private int teacherId;
    private double fullAmount;
    private  double commission;
    private double teacherAmount;
    private Date withdrawDate;
}
