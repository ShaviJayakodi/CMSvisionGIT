package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.HallDTO;
import com.example.Vision.CMSvision.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/hallController")
public class HallController {

    @Autowired
    private HallService hallService;

    @PostMapping("/saveHall")
    public HallDTO saveHall(@RequestBody HallDTO hallDTO)
    {
        return hallService.saveHall(hallDTO);
    }

    @GetMapping("/getAllHalls")
    public List<HallDTO> getAllHalls()
    {
        return hallService.getAllHalls();
    }

    @GetMapping("/getByHallId")
    public HallDTO getByHallId(@RequestParam int hallId)
    {
        return hallService.getByHallId(hallId);
    }

    @DeleteMapping("/deleteHall")
    public String deleteHall(@RequestParam int hallId)
    {
        return hallService.deleteById(hallId);
    }

}
