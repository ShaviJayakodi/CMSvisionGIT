package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.ClassInfoDTO;
import com.example.Vision.CMSvision.entity.ClassInfo;
import com.example.Vision.CMSvision.entity.Grade;
import com.example.Vision.CMSvision.entity.Subject;
import com.example.Vision.CMSvision.entity.Teacher;
import com.example.Vision.CMSvision.repo.ClassInfoRepo;
import com.example.Vision.CMSvision.repo.GradeRepo;
import com.example.Vision.CMSvision.repo.SubjectRepo;
import com.example.Vision.CMSvision.repo.TeacherRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClassInfoService {

   @Autowired
    private ClassInfoRepo classInfoRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private GradeRepo gradeRepo;

    @Autowired
    ModelMapper modelMapper;
    public ClassInfoDTO saveClass(ClassInfoDTO classInfoDTO)
    {
        ClassInfoDTO classInfo = new ClassInfoDTO();
        Teacher teacher = modelMapper.map(teacherRepo.findById(classInfoDTO.getTeacherId()).get(),Teacher.class);
        Subject subject = modelMapper.map(subjectRepo.findById(classInfoDTO.getSubjectId()).get(),Subject.class);
        Grade grade = modelMapper.map(gradeRepo.findById(classInfoDTO.getGradeId()).get(),Grade.class);

        try {
            System.out.println(teacher.getFirstName() + " " + subject.getSubjectName() + " " + grade.getGradeCode());
            classInfo.setClassType(classInfoDTO.getClassType());
            classInfo.setGradeId(grade.getGradeId());
            classInfo.setTeacherId(grade.getGradeId());
            classInfo.setSubjectId(subject.getSubjectId());
            classInfoRepo.save(modelMapper.map(classInfo, ClassInfo.class));

        }
        catch (Exception exception)
        {
            throw exception;
        }
return classInfo;
    }

}
