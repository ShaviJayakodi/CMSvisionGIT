package com.example.Vision.CMSvision.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classFeeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_studentId")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_classId")
    private ClassInfo classInfo;

    private int teacherId;
    private String month;
    private double amount;

    private Date payDate;
    private String payMethod;
    private int isWithdraw;//byTeachers
    private int isRelease;



}
