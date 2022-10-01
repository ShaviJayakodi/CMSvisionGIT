package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.MarkAttendanceDTO;
import com.example.Vision.CMSvision.entity.ClassInfo;
import com.example.Vision.CMSvision.entity.MarkAttendance;
import com.example.Vision.CMSvision.entity.OpenClass;
import com.example.Vision.CMSvision.entity.Student;
import com.example.Vision.CMSvision.repo.ClassInfoRepo;
import com.example.Vision.CMSvision.repo.OpenClassRepo;
import com.example.Vision.CMSvision.repo.StudentRepo;
import com.example.Vision.CMSvision.repo.MarkAttendnaceRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MarkAttendanceService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ClassInfoRepo classInfoRepo;
    @Autowired
    private OpenClassRepo openClassRepo;
    @Autowired
    private MarkAttendnaceRepo markAttendnaceRepo;
    @Autowired
    private ModelMapper modelMapper;
    //attendance
    public MarkAttendanceDTO markAttendance(int studentId,int openClassId)
    {
        Student student = modelMapper.map(studentRepo.findById(studentId).get(),Student.class);

        OpenClass openClass = modelMapper.map(openClassRepo.findById(openClassId).get(),OpenClass.class);
        ClassInfo classInfo = modelMapper.map(classInfoRepo.findById(openClass.getClassInfo().getClassId()).get(),ClassInfo.class);
        MarkAttendanceDTO markAttendanceDTO = new MarkAttendanceDTO();
        MarkAttendance mark = new MarkAttendance();
        Date classDate = openClass.getOpenDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dDate = new Date();
        System.out.println(classDate);
        int classId = classInfo.getClassId();

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        MarkAttendance student1=  markAttendnaceRepo.getbyStudentClassAndOpen(studentId,classId,openClassId);
        System.out.println(student1);

        Date date = student1.getAttendanceDate();
        String new1 =classDate.toString();
        String new2= date.toString();

        System.out.println(new1+"    "+new2);
        markAttendanceDTO = modelMapper.map(student1,MarkAttendanceDTO.class);
            String status ="";

            if(new1.equals(new2))
        {
            System.out.println("true");
        }
        else
        {
            System.out.println("false");
        }

        return markAttendanceDTO;
    }







}
