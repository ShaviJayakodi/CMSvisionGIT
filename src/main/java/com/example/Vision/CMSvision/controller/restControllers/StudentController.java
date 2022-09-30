package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.StudentDTO;
import com.example.Vision.CMSvision.entity.Student;
import com.example.Vision.CMSvision.repo.StudentRepo;
import com.example.Vision.CMSvision.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/studentController")

public class StudentController {


    @Autowired
    private StudentService studentService;

    @PostMapping("/saveStudent")
    public StudentDTO saveNewStudent(@RequestBody StudentDTO studentDTO)
    {
        return studentService.saveNewStudent(studentDTO);
    }

    @GetMapping("/getAllStudent")
    public List<StudentDTO> getAllStudent()
    {
        return studentService.getAllStudent();
    }

    @GetMapping("/getUniqueById")
    public Student getUniqueBYId(@RequestParam int studentId)
    {
       return studentService.getUniqueById(studentId);
    }

    @GetMapping("/getUniqueByRegNo")
    public Student getUniqueByRegNo (@RequestParam int regNo)
    {
        return studentService.getUniqueByRegNo(regNo);
    }

    @DeleteMapping("/deleteStudentById")
    public String deleteById(@RequestParam int studentId)
    {
        return studentService.deleteById(studentId);
    }

    @GetMapping("/studentGetByGradeId")
    public List<Student> studentGetByGradeId(@RequestParam int gradeId)
    {
        return studentService.getStudentByGradeId(gradeId);
    }


}
