package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClassInfoRepo extends JpaRepository<ClassInfo,Integer> {

    @Query(value = "SELECT*FROM class_info WHERE teacher_teacher_id =?1",nativeQuery = true)
    List<ClassInfo> getClassInfoByTeacherId(int teacherId);
}
