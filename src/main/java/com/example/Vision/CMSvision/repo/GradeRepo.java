package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.dto.GradeDTO;
import com.example.Vision.CMSvision.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepo extends JpaRepository<Grade,Integer> {
     @Query(value ="SELECT max (gradeId) from Grade",nativeQuery = true)
     int getMaxGradeId();

   //  @Query(value ="SELECT * FROM grade WHERE grade_code LIKE 'G06'",nativeQuery = true)

     //String findByGradeCode(String gradeCode);
    default GradeDTO findByGradeCode(String gradeCode)
     {
         GradeDTO gradeDTO = new GradeDTO();
         //System.out.println(gradeDTO);
         return  gradeDTO;
     }


}
