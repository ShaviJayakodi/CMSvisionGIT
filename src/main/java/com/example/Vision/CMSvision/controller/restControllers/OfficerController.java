package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.OfficerDTO;
import com.example.Vision.CMSvision.service.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/officerController")
public class OfficerController {
    @Autowired
    private OfficerService officerService;

    @PostMapping("/saveOfficer")
    public OfficerDTO saveOfficer(@RequestBody OfficerDTO officerDTO)
    {
        return officerService.saveOfficer(officerDTO);
    }

    @GetMapping("/getAllOfficers")
    public List<OfficerDTO> getAllOfficers()
    {
        return officerService.getAllOfficers();
    }

    @GetMapping("/getUniqueByRegNo")
    public OfficerDTO getOfficerByRegNo(@RequestParam int officerRegNo)
    {
        System.out.println(officerRegNo);
        return officerService.getOfficerByRegNo(officerRegNo);
    }

    @GetMapping("/getUniqueByOfficerId")
    public OfficerDTO getOfficerById(@RequestParam int officerId)
    {
        return officerService.getOfficerById(officerId);
    }

    @DeleteMapping("/deleteOfficerById")
    public String deleteOfficer(@RequestParam int officerId)
    {
        return officerService.deleteOfficerById(officerId);
    }
}
