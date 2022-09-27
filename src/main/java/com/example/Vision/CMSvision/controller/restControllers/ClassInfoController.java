package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.ClassInfoDTO;
import com.example.Vision.CMSvision.entity.ClassInfo;
import com.example.Vision.CMSvision.repo.ClassInfoRepo;
import com.example.Vision.CMSvision.service.ClassInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;

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
       return classInfoService.saveClass(classInfoDTO);
    }

}
