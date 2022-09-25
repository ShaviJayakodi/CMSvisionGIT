package com.example.Vision.CMSvision.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int status;


}
