package com.example.Vision.CMSvision.dto;

import com.example.Vision.CMSvision.entity.ClassInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenClassDTO {

    private int openClassId;
    private Date openDate;
    private int status;
    private int classInfoId;
}
