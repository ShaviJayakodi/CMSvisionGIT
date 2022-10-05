package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.UserDTO;
import com.example.Vision.CMSvision.entity.User;
import com.example.Vision.CMSvision.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/userController")
public class userController {
    @Autowired
    private UserService userService;

    @PostMapping("/UserSave")
    public UserDTO save(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        return userService.userSave(userDTO);
    }

    @GetMapping("/search")
    public UserDTO search(@RequestParam int userId)
    {
        System.out.println(userId);
        return userService.findById(userId);
    }

    @DeleteMapping("/UserDelete")
    public  String deleteById(@RequestParam int userId)
    {
        System.out.println(userId);
        return userService.deleteById(userId);
    }
}
