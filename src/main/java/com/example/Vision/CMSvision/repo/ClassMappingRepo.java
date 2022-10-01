package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.dto.ClassMappingDTO;
import com.example.Vision.CMSvision.entity.ClassMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface ClassMappingRepo extends JpaRepository<ClassMapping , Integer> {

    @Query(value = "SELECT * FROM class_mapping WHERE student_student_id=?1",nativeQuery = true)
    List<ClassMapping> getClassMappingByStudentId(int studentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM class_mapping WHERE class_mapping_id=?1",nativeQuery = true)
    int deleteByClassMappingId(int mappingId);

    @Query(value = "SELECT* FROM class_mapping WHERE class_mapping_id =>1",nativeQuery = true)
    ClassMappingDTO getClassMappingByClassMappingId(int mappingId);

   // @Query(value = "delete from ClassMapping where classMappingId=?1")

    @Query(value = "SELECT * FROM class_mapping",nativeQuery = true)
    List<ClassMapping> getAll();




}
