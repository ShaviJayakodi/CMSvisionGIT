package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.ClassMappingDTO;
import com.example.Vision.CMSvision.entity.ClassMapping;
import com.example.Vision.CMSvision.repo.ClassMappingRepo;
import com.example.Vision.CMSvision.service.ClassMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/classMappingController")
public class ClassMappingController {
    @Autowired
    private ClassMappingService classMappingService;
    @Autowired
    private ClassMappingRepo classMappingRepo;
    @PostMapping("/saveClassMap")
    public ClassMappingDTO saveMap (@RequestBody ClassMappingDTO classMapping)
    {

        System.out.println(classMapping);
        return classMappingService.saveClassMap(classMapping);
    }

    @GetMapping("/getMappingsByStudentId")
    public List<ClassMappingDTO> getByStudentId(@RequestParam int studentId)
    {
        return classMappingService.getMappingsByStudentId(studentId);
    }

    @DeleteMapping("/deleteMappingByMappingId")
    @Transactional
    public int deleteByMappingId(@RequestParam int mappingId)
    {
            return classMappingService.deleteById(mappingId);

    }
}
