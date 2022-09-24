package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.dto.StudentDTO;
import com.example.Vision.CMSvision.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query(value ="SELECT max (studentId) from Student",nativeQuery = true)
    int getMaxGradeId();
    //List<StudentDTO> findByStudentId(int studentId);
    List<StudentDTO> findByGradeId(String gradeId);
    List<StudentDTO> findByFirstName(String firstName);
    List<Student> findByStudentId(int studentId);

}
