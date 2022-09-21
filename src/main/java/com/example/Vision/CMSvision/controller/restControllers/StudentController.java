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

public class StudentController {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private StudentService studentService;

    @PostMapping("/saveStudent")
    public StudentDTO saveNewStudent(@RequestBody StudentDTO studentDTO)
    {
         return studentService.saveNewStudent(studentDTO);

    }
    @PostMapping("/save")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO)
    {
        return new ResponseEntity<StudentDTO>(studentService.saveNewStudent(studentDTO), HttpStatus.OK);
    }

    @GetMapping("/getByStudentId")
    public ResponseEntity<List<Student>> getStudentById(@RequestParam int studentId)
    {
        return new ResponseEntity<List<Student>>(studentRepo.findByStudentId(studentId), HttpStatus.OK);
    }
}
