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
public class OpenClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int openClassId;
    private Date openDate;
    private int status;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "class_classId")
    private ClassInfo classInfo;
}
