package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.GradeDTO;
import com.example.Vision.CMSvision.entity.Grade;
import com.example.Vision.CMSvision.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/gradeController")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @PostMapping("/saveGrade")
    public Grade saveGrade(@RequestBody GradeDTO gradeDTO)
    {
        System.out.println(gradeDTO);
        return gradeService.saveGrade(gradeDTO);
    }
   /* @GetMapping("/getByGradeCode")
    public GradeDTO findByGradeCode(@RequestParam int graadeId)
    {
        System.out.println(gradeCode);
        return gradeService.findByGradeCode(gradeCode);

    }*/

    @GetMapping("/getUniqueDetailsById")
    public GradeDTO getUniqueDetailsById(@RequestParam int gradeId)
    {
        System.out.println(gradeId);
        return gradeService.findByGradeId(gradeId);
    }

    @GetMapping("/getAll")
    public List<GradeDTO> getAllGrades()
    {
        return gradeService.getAllGrade();
    }

    @PutMapping("/updateGrade")
    public GradeDTO update (@RequestBody GradeDTO gradeDTO)
    {
        return gradeService.update(gradeDTO);
    }

    @DeleteMapping("/deleteGrade")
    public String deleteByGradeId(int gradeId)
    {
        return gradeService.deleteByGradeId(gradeId);
    }
}
