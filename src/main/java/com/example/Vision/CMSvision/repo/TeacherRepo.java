package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Integer> {

    @Query(value = "SELECT MAX(teacherId)from Teacher" )
    int getMaxTeacherId();



    @Query(value = "SELECT *  FROM teacher WHERE regNo=?1",nativeQuery = true)
    Teacher getTeachersByRegNO(int regNo);


}
