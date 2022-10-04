package com.example.Vision.CMSvision.dto;

import com.example.Vision.CMSvision.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private int studentId;
    private String regNo;
    private String firstName;
    private String lastName;
    private String address;
    private Date birthDay;
    private Date attendDate;
    private String parentName;
    private String relationship;
    private String mob1;
    private int gradeId;
    private String school;
    private String mob2;
    private String gender;
    private int status;


}

