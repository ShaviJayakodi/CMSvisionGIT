package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.ClassMappingDTO;
import com.example.Vision.CMSvision.entity.ClassMapping;
import com.example.Vision.CMSvision.service.ClassMappingService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

@RestController
@CrossOrigin
@RequestMapping("/classMappingController")
public class classMappingController {
    @Autowired
    private ClassMappingService classMappingService;



    @PostMapping("/saveClassMap")
    public ClassMapping saveClassMap (@RequestBody ClassMapping classMapping)
    {
        System.out.println(classMapping);
        return null;
    }


}
