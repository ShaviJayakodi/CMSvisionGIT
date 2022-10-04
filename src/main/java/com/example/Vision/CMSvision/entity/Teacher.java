package com.example.Vision.CMSvision.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    @Temporal(TemporalType.DATE)
    private  Date attendDate;
    private String address;
    private String gender;
    private String mobNum1;
    private String mobNum2;
    private String emailAddress;
    private int regNO;
    private int status;
    private int commission;



}
