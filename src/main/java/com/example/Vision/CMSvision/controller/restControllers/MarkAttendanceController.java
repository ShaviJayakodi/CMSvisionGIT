package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.MarkAttendanceDTO;
import com.example.Vision.CMSvision.dto.OpenClassDTO;
import com.example.Vision.CMSvision.entity.MarkAttendance;
import com.example.Vision.CMSvision.entity.OpenClass;
import com.example.Vision.CMSvision.service.MarkAttendanceService;
import com.example.Vision.CMSvision.service.OpenClassService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/markAttendanceController")
public class MarkAttendanceController {
    @Autowired
    private MarkAttendanceService markService;

    @Autowired
    private OpenClassService openClassService;

    /*@GetMapping("/mark")
    public MarkAttendanceDTO getOpenedClassesForStudent (int studentId,int openClassId)
    {
        return markService.getOpenedClassesForStudent(studentId,openClassId);
    }*/

    @GetMapping("/getByRegNo")
    public List<OpenClass> save(int regNo)
    {
        return markService.getOpenedClassesForStudent(regNo);
    }


    @PostMapping("/markAttendance")
    public ResponseEntity<?> markAttendance(@RequestBody MarkAttendanceDTO markDTO)
    {
        return markService.addAttendance(markDTO);
    }

    @GetMapping("/getAll")
    public List<MarkAttendanceDTO> getAll()
    {
        return markService.getAll();

    }

    @GetMapping("/getAttendanceReviewForStudentId")
    @Temporal(TemporalType.DATE)
    public List<MarkAttendance> getAttendanceReviewForStudentId(@RequestParam int studentId, @RequestParam int classId,@RequestParam String fromDate, @RequestParam String toDate) throws ParseException {
        System.out.println(studentId+" "+classId);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fDate = format.parse(fromDate);
        Date tDate = format.parse(toDate);
        System.out.println(fDate+" "+tDate);


        return markService.getAttendanceReviewForStudentId(studentId,classId,fDate,tDate);

    }



}
