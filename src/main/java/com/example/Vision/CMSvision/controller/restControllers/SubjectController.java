package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.SubjectDTO;
import com.example.Vision.CMSvision.entity.Subject;
import com.example.Vision.CMSvision.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/subjectController")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/getAll")
    public List<SubjectDTO> getAllSubjects()
    {

        System.out.println(subjectService.getAllSubjects());
        return subjectService.getAllSubjects();
    }

    @PostMapping("/saveSubject")
    public SubjectDTO saveSubject(@RequestBody SubjectDTO subjectDTO)
    {
        return subjectService.saveSubject(subjectDTO);
    }

    @GetMapping("/getDetailsById")
    public SubjectDTO getUniqueDetailsById(@RequestParam int subjectId)
    {
        System.out.println(subjectId);
        System.out.println(subjectService.getUniqueById(subjectId));
        return subjectService.getUniqueById(subjectId);
    }



}
