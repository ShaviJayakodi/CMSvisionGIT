package com.example.Vision.CMSvision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassMappingDTO {

    private int classMappingId;
    private int studentId;
    private int classInfoId;
    private Date attendDate;
    private int status;
    private int classYear;
    private String classCode;
    private String paymentMethod;
}
