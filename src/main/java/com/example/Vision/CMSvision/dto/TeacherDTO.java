package com.example.Vision.CMSvision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private int teacherId;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private  Date attendDate;
    private String address;
    private String gender;
    private String mobNum1;
    private String mobNum2;
    private String emailAddress;
    private int regNO;

}
