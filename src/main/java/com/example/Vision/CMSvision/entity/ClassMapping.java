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
public class ClassMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classMappingId;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "student_studentId")
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "class_classId")
    private ClassInfo classInfo;
    @Temporal(TemporalType.DATE)
    private Date attendDate;
    private int status;
    private String classCode;
    private int classYear;
    private String paymentMethod;

}
