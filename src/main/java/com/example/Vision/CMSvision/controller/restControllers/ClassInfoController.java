package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.ClassInfoDTO;
import com.example.Vision.CMSvision.entity.ClassInfo;
import com.example.Vision.CMSvision.repo.ClassInfoRepo;
import com.example.Vision.CMSvision.service.ClassInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/classController")
public class ClassInfoController {

    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/saveClass")
    public ClassInfoDTO saveClass(@RequestBody ClassInfoDTO classInfoDTO)
    {
        System.out.println(classInfoDTO);
       return classInfoService.saveClass(classInfoDTO);
    }

    @GetMapping("/getAllClass")
    public List<ClassInfo> getAllClass()
    {
        return classInfoService.getAllClass();
    }


    @GetMapping("/getAllClassByTeacher")
    public List<ClassInfo> getAllClassByTeacher(@RequestParam int teacherId)
    {
        return classInfoService.getAllClassByTeacherId(teacherId);
    }

    @GetMapping("/getClassByClassId")
    public ClassInfo getClassByClassId(@RequestParam int classId)
    {
        return classInfoService.getClassByClassId(classId);
    }

    @GetMapping("/getClassesByGradeId")
    public List<ClassInfo> getClassesByGradeId(@RequestParam int gradeId)
    {
        return classInfoService.getClassesByGradeId(gradeId);
    }




}
