package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/loginController")
public class loginController {


    @Autowired
    private loginService loginService;


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String userName, @RequestParam String passWord)
    {
        System.out.println(userName+ " " + passWord);
        loginService.login(userName,passWord);
        return null;
    }

}
