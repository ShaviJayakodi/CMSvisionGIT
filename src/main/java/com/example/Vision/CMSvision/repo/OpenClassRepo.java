package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.OpenClass;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OpenClassRepo extends JpaRepository<OpenClass, Integer> {

    @Query(value = "SELECT* FROM open_class WHERE status =?1",nativeQuery = true)
    List<OpenClass> findActiveClasses(int status);

    //@Query(value = "SELECT * FROM open_class WHERE class_class_id = ?1 AND open_date >=?2 and open_date <=?3;",nativeQuery = true)

    @Query(value ="select c from OpenClass c where c.classInfo.classId =:studentId and c.openDate>= :fromDate and c.openDate<= :toDate")
    List<OpenClass> getOpenClassByClassInfoClassId (@Param("studentId") int classId, @Param("fromDate") java.util.Date fromDate, @Param("toDate") Date toDate);

}
