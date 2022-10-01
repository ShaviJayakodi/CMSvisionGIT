package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.MarkAttendanceDTO;
import com.example.Vision.CMSvision.dto.OpenClassDTO;
import com.example.Vision.CMSvision.entity.OpenClass;
import com.example.Vision.CMSvision.service.MarkAttendanceService;
import com.example.Vision.CMSvision.service.OpenClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/markAttendanceController")
public class MarkAttendanceController {
    @Autowired
    private MarkAttendanceService markService;

    @Autowired
    private OpenClassService openClassService;

    @GetMapping("/mark")
    public MarkAttendanceDTO markAttendance (int studentId,int openClassId)
    {
        return markService.markAttendance(studentId,openClassId);
    }

    @GetMapping("/getByRegNo")
    public List<OpenClass> ssave(int regNo)
    {
        return openClassService.save(regNo);
    }
}
