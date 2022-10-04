package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.dto.ClassFeeDTO;
import com.example.Vision.CMSvision.dto.ClassMappingDTO;
import com.example.Vision.CMSvision.entity.ClassFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassFeeRepo extends JpaRepository<ClassFee,Integer> {

    @Query(value = "SELECT * FROM class_fee WHERE student_student_id=?1 AND `month`=?2 AND `year`=?3 AND class_class_id=?4",nativeQuery = true)
    ClassFee checkClassFee(int studentId,int month, int year, int classId);

    @Query(value = "SELECT * FROM class_fee WHERE class_class_id=?1 AND is_withdraw=?2",nativeQuery = true)
    List<ClassFee> activeClassFeeListToByClassId(int classId, int sts);
}
