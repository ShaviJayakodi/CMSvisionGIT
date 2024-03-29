package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.TeacherDTO;
import com.example.Vision.CMSvision.entity.Teacher;
import com.example.Vision.CMSvision.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("saveTeacher")
    public ResponseEntity<?> saveTeacher(@RequestBody TeacherDTO teacherDTO)
    {
        System.out.println(teacherDTO);
        return teacherService.saveTeacher(teacherDTO);
    }

    @GetMapping("/getUnique")
    public TeacherDTO getUnique(@RequestParam int regNo)
    {
        return teacherService.getByRegNO(regNo);
    }

    @GetMapping("/getAllTeachers")
    public List<TeacherDTO> getAllTeachers()
    {
        return teacherService.findAllTeachers();
    }

    @GetMapping("/getById")
    public Teacher getById(@RequestParam int teacherId)
    {

        return teacherService.findById(teacherId);

    }

    @DeleteMapping("/teacherDelete")
    public String teacherDelete(@RequestParam int teacherId)
    {
        System.out.println(teacherId);
        System.out.println(teacherService.deleteByTeacherId(teacherId));
        return teacherService.deleteByTeacherId(teacherId);
    }

}
