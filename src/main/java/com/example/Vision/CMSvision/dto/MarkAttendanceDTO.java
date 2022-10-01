package com.example.Vision.CMSvision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkAttendanceDTO {

    private int attendanceId;
    private int classInfoId;
    private int studentId;
    private int openClassId;
    private Date attendanceDate;
}
