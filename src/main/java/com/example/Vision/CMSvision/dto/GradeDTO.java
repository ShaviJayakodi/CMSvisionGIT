package com.example.Vision.CMSvision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private int gradeId;
    private String gradeCode;
    private String gradeDescription;
}
