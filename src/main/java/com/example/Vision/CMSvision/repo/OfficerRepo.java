package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.Officer;
import com.example.Vision.CMSvision.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficerRepo extends JpaRepository<Officer,Integer> {

    @Query(value = "SELECT MAX(officerId)from Officer" )
    int getMaxOfficerId();

    @Query(value = "SELECT *  FROM officer WHERE reg_no=?1",nativeQuery = true)
    Officer getOfficerByRegNO(int officerRegNo);

}
