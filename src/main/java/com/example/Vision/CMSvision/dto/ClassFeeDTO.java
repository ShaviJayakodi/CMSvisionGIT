package com.example.Vision.CMSvision.dto;

import com.example.Vision.CMSvision.entity.ClassInfo;
import com.example.Vision.CMSvision.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassFeeDTO {
    private int classFeeId;
    private int studentId;
    private int classId;
    private int teacherId;
    private int month;
    private int year;
    private double amount;
    private Date payDate;
    private String payMethod;
    private int isWithdraw;//byTeachers
    private int isRelease;
    private int authorizedBy;
}
