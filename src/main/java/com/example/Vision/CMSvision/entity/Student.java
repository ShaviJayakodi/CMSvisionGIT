package com.example.Vision.CMSvision.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne(cascade = CascadeType.ALL)
    private Grade gradeId;
    private String school;
    private String landNumber;
    private String gender;


}
