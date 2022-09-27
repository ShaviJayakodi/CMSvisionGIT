package com.example.Vision.CMSvision.service;


import com.example.Vision.CMSvision.dto.GradeDTO;
import com.example.Vision.CMSvision.dto.StudentDTO;
import com.example.Vision.CMSvision.entity.Grade;
import com.example.Vision.CMSvision.entity.Student;
import com.example.Vision.CMSvision.repo.GradeRepo;
import com.example.Vision.CMSvision.repo.StudentRepo;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private GradeRepo gradeRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional

    public StudentDTO saveNewStudent(StudentDTO studentDTO) {

        StudentDTO student = new StudentDTO();
        try {

            Grade grade =modelMapper.map(gradeRepo.findById(studentDTO.getGradeId()).get(),Grade.class);

            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setAddress(studentDTO.getAddress());
            student.setSchool(studentDTO.getSchool());
            student.setAttendDate(studentDTO.getAttendDate());
            student.setBirthDay(studentDTO.getBirthDay());
            student.setGradeId(grade.getGradeId());
            System.out.println(student);
            studentRepo.save(modelMapper.map(student, Student.class));

        }
        catch (Exception exception)
        {
            System.out.println("something went wrong"+exception);
        }

        return student;
    }
}
