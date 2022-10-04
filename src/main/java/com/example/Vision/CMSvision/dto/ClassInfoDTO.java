package com.example.Vision.CMSvision.dto;

import com.example.Vision.CMSvision.entity.Grade;
import com.example.Vision.CMSvision.entity.Subject;
import com.example.Vision.CMSvision.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfoDTO {
    private int classId;
    private int teacherId;
    private int subjectId;
    private int gradeId;
    private String classCode;
    private String classType;
    private double fullFee;
    private double halfFee;
    private double freeFee;
    private int status;
}

