package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.entity.User;
import com.example.Vision.CMSvision.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class loginService {
    @Autowired
    private UserRepo userRepo;

    public User login (String userName,String passWord)
    {
        return userRepo.checkUser(userName,passWord);

    }

}
