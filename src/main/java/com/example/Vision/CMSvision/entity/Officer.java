package com.example.Vision.CMSvision.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    @Temporal(TemporalType.DATE)
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
