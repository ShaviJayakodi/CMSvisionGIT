package com.example.Vision.CMSvision.entity;

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
    private String mob1;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_gradeId",nullable = false)
    private Grade grade;
    private String school;
    private String mob2;
    private String gender;
    private int status;


}
