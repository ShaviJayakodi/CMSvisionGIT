package com.example.Vision.CMSvision.dto;

import com.example.Vision.CMSvision.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private int studentId;
    private String regNo;
    private String firstName;
    private String lastName;
    private String address;
    private String birthDay;
    private String attendDate;
    private String parentName;
    private String relationship;
    private String mobileNumber;
    private int gradeId;
    private String school;
    private String landNumber;
    private String gender;

}

