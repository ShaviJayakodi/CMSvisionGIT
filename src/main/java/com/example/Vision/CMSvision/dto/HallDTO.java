package com.example.Vision.CMSvision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallDTO {
    private int hallId;
    private String hallCode;
    private String hallName;
    private String floor;
}
