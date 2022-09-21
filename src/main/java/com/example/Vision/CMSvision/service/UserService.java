package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.UserDTO;
import com.example.Vision.CMSvision.entity.User;
import com.example.Vision.CMSvision.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO userSave(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO, User.class));
        System.out.println(userDTO);
        return userDTO;
    }

    public UserDTO findById(int userId) {
        return modelMapper.map(userRepo.findById(userId).get(),UserDTO.class);
    }
    public String deleteById(int userId)
    {
        String value="";
      if (userRepo.existsById(userId)){

        userRepo.deleteById(userId);
        System.out.println(value);
        }
      else
       {
           System.out.println("User Id Not Valid");
       }


       return null;
    }



}
