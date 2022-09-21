package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.GradeDTO;

import com.example.Vision.CMSvision.entity.Grade;

import com.example.Vision.CMSvision.repo.GradeRepo;

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
    {
        Grade grade = new Grade();

        //gradeRepo.save(modelMapper.map(gradeDTO, Grade.class));
        int maxId = gradeRepo.getMaxGradeId();
        grade.setGradeCode(gradeDTO.getGradeCode());
        grade.setGradeDescription(gradeDTO.getGradeDescription());
        int regNo= Integer.parseInt(commonService.genarateRegNo(maxId));
        gradeRepo.save(modelMapper.map(grade,Grade.class));
        return grade;
    }
   /* public Grade findByGradeCode(String gradeCode)
    {

        System.out.println(gradeCode);
        return modelMapper.map(gradeRepo.findByGradeCode(gradeCode).compareTo(gradeCode),Grade.class);

    }*/

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
}
