package com.example.Vision.CMSvision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficerDTO {
    private int officerId;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private Date attendDate;
    private String address;
    private String dutyType;
    private String gender;
    public String mobNum1;
    public String mobNum2;
    public String emailAddress;
    private int regNo;
}
