package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.dto.StudentDTO;
import com.example.Vision.CMSvision.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query(value ="SELECT MAX(student_id) FROM student",nativeQuery = true)
    int getMaxGradeId();

    @Query(value = "SELECT * FROM student WHERE reg_no=?1",nativeQuery = true)
    Student getUniqueStudentByRegNo(int regNo);


    @Query(value = "SELECT * FROM student WHERE grade_grade_id=?1",nativeQuery = true)
    List<Student> getStudentsByGradeId(int gradeId);


}
