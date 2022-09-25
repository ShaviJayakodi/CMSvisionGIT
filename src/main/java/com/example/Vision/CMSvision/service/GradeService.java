package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.GradeDTO;

import com.example.Vision.CMSvision.dto.UserDTO;
import com.example.Vision.CMSvision.entity.Grade;

import com.example.Vision.CMSvision.repo.GradeRepo;

import com.example.Vision.CMSvision.repo.StudentRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class GradeService {

    @Autowired
    private GradeRepo gradeRepo;

    @Autowired
   private ModelMapper modelMapper;

    @Autowired
    private CommonService commonService;


    public Grade saveGrade(GradeDTO gradeDTO)
    {   //gradeRepo.save(modelMapper.map(gradeDTO, Grade.class));
        //int maxId = gradeRepo.getMaxGradeId();
        // int regNo= Integer.parseInt(commonService.genarateRegNo(maxId));

       Grade grade = new Grade();
        if(gradeDTO.getGradeId()>0)
        {
            grade.setGradeId(gradeDTO.getGradeId());
            grade.setGradeCode(gradeDTO.getGradeCode());
            grade.setGradeDescription(gradeDTO.getGradeDescription());
        }
        else
        {
            grade.setGradeCode(gradeDTO.getGradeCode());
            grade.setGradeDescription(gradeDTO.getGradeDescription());
        }

        gradeRepo.save(modelMapper.map(grade,Grade.class));
        return grade;
    }


    public GradeDTO findByGradeCode(int gradeCode)
    {
        return modelMapper.map(gradeRepo.findById(gradeCode).get(),GradeDTO.class);

    }
    public List<GradeDTO> getAllGrade()
    {
       List<Grade> gradeList= gradeRepo.findAll();
       // System.out.println(gradeList);
       return modelMapper.map(gradeList,new TypeToken<List<GradeDTO>>(){}.getType());
    }

    public GradeDTO findByGradeId(int gradeId) {
            return modelMapper.map(gradeRepo.findById(gradeId).get(),GradeDTO.class);

    }


    public GradeDTO update(GradeDTO gradeDTO)
    {
        GradeDTO grade = new GradeDTO();
        grade.setGradeId(gradeDTO.getGradeId());
        grade.setGradeCode(gradeDTO.getGradeCode());
        grade.setGradeDescription(gradeDTO.getGradeDescription());
        gradeRepo.save(modelMapper.map(grade,Grade.class));
        return grade;
    }

    public String deleteByGradeId(int gradeId)
    {
        String data="";
        if(gradeRepo.existsById(gradeId))
        {
            gradeRepo.deleteById(gradeId);
            data+="Successfully Deleted";
        }
        else
        {
            data+= "Grade Id Not Exist";
        }
        return data;
    }

}
