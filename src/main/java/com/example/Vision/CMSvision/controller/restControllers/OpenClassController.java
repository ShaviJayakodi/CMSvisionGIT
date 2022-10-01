package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.OpenClassDTO;
import com.example.Vision.CMSvision.entity.OpenClass;
import com.example.Vision.CMSvision.service.OpenClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/openClassController")
@CrossOrigin
public class OpenClassController {
    @Autowired
    private OpenClassService openClassService;

    @PostMapping("/openClass")
    public OpenClassDTO openClass(@RequestBody OpenClassDTO openClassDTO)
    {
        System.out.println(openClassDTO);
        return openClassService.addOpenClass(openClassDTO);
    }

    @GetMapping("/getAllOpenedClass")
    public List<OpenClass> getAllOpenedClass()
    {
        return openClassService.getAllOpenedClass();
    }

    @GetMapping("/openClassGetById")
    public OpenClass openClassGetById(@RequestParam int openClassId)
    {
        return openClassService.openClassGetById(openClassId);
    }

}
