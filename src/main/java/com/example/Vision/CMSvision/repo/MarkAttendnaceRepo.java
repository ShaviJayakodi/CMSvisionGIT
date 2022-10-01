package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.dto.MarkAttendanceDTO;
import com.example.Vision.CMSvision.entity.MarkAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkAttendnaceRepo extends JpaRepository<MarkAttendance,Integer> {

    @Query(value = "SELECT * FROM mark_attendance WHERE student_student_id=?1 AND class_class_id=?2 AND opened_class_id=?3",nativeQuery = true)
    MarkAttendance getbyStudentClassAndOpen(int studentId, int classId, int openClassId);

}
