package com.example.Vision.CMSvision.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_teacherId")
    private Teacher teacher;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_gradeId")
    private Subject subject;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_subjectId")
    private Grade grade;
    private String classCode;
    private String classType;
    private double fullFee;
    private double halfFee;
    private double freeFee;
    private int status;
}
