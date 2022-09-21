package com.example.Vision.CMSvision.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
public class CommonService {
    public String genarateRegNo(int maxId)
    {
        System.out.println(maxId);
        String regNo="";
        LocalDateTime currantDate = LocalDateTime.now();
        LocalDate date1 = currantDate.toLocalDate();
        int year1 =date1.getYear();
        int year= Integer.parseInt((Integer.toString(year1)).substring(2,4));
        String pattern = "0000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(maxId+1);
        regNo=Integer.toString(year)+format;

        System.out.println(regNo);
        return regNo;
    }
}
