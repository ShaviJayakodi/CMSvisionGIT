package com.example.Vision.CMSvision.service;


import com.example.Vision.CMSvision.dto.StudentDTO;
import com.example.Vision.CMSvision.entity.Student;
import com.example.Vision.CMSvision.repo.StudentRepo;
import org.hibernate.Criteria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Student saveNewStudent(StudentDTO studentDTO)
    {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setGradeId(studentDTO.getGradeId());
        student.setAddress(studentDTO.getAddress());
        student.setSchool(studentDTO.getSchool());
        studentRepo.save(modelMapper.map(student, Student.class));

        return student;
    }
}
