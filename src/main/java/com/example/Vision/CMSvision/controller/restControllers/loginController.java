package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.entity.User;
import com.example.Vision.CMSvision.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/loginController")
public class loginController {


    @Autowired
    private loginService loginService;




}
