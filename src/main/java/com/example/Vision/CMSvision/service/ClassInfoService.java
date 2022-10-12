package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.ClassInfoDTO;
import com.example.Vision.CMSvision.entity.ClassInfo;
import com.example.Vision.CMSvision.entity.Grade;
import com.example.Vision.CMSvision.entity.Subject;
import com.example.Vision.CMSvision.entity.Teacher;
import com.example.Vision.CMSvision.enums.statusValue;
import com.example.Vision.CMSvision.repo.ClassInfoRepo;
import com.example.Vision.CMSvision.repo.GradeRepo;
import com.example.Vision.CMSvision.repo.SubjectRepo;
import com.example.Vision.CMSvision.repo.TeacherRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
      ClassInfoDTO dto = new ClassInfoDTO();
      ClassInfo classInfo = new ClassInfo();
      Grade grade =modelMapper.map(gradeRepo.findById(classInfoDTO.getGradeId()).get(),Grade.class);
      Teacher teacher = modelMapper.map(teacherRepo.findById(classInfoDTO.getTeacherId()).get(),Teacher.class);
      Subject subject = modelMapper.map(subjectRepo.findById(classInfoDTO.getSubjectId()).get(),Subject.class);

      try {
          if(classInfoDTO.getClassId()>0)
          {
              classInfo.setClassId(classInfo.getClassId());
              classInfo.setTeacher(teacher);
              classInfo.setGrade(grade);
              classInfo.setSubject(subject);
              classInfo.setClassType(classInfoDTO.getClassType());
              classInfo.setFreeFee(classInfoDTO.getFullFee());
              classInfo.setHalfFee(classInfoDTO.getHalfFee());
              classInfo.setFreeFee(classInfoDTO.getFreeFee());
              classInfo.setClassCode(classInfoDTO.getClassCode());
              classInfo.setStatus(statusValue.ACTIVE.sts());

          }
          else
          {
              classInfo.setTeacher(teacher);
              classInfo.setGrade(grade);
              classInfo.setSubject(subject);
              classInfo.setClassType(classInfoDTO.getClassType());
              classInfo.setFullFee(classInfoDTO.getFullFee());
              classInfo.setHalfFee(classInfoDTO.getHalfFee());
              classInfo.setFreeFee(classInfoDTO.getFreeFee());
              classInfo.setClassCode(
                      teacher.getFirstName().substring(0,3).toUpperCase()+"-"+
                              subject.getSubjectCode().toUpperCase()+"-"+
                              grade.getGradeCode().toUpperCase()+"-"+
                              classInfoDTO.getClassType().substring(0,3).toUpperCase()
              );
              classInfo.setStatus(statusValue.ACTIVE.sts());



          }
          classInfo=classInfoRepo.save(classInfo);

          dto = modelMapper.map(classInfo,ClassInfoDTO.class);
          System.out.println(dto);

        }
      catch (RuntimeException exception)
      {
          throw exception;
      }

    return dto;
    }

    public List<ClassInfo> getAllClass()
    {
       List<ClassInfo> classList= classInfoRepo.findAll();
        return classList;
    }

    public List<ClassInfo> getAllClassByTeacherId(int teacherId)
    {
        List<ClassInfo> classListByTeacher = classInfoRepo.getClassInfoByTeacherId(teacherId);
        return classListByTeacher;
    }

    public ClassInfo getClassByClassId(int classId) {
        ClassInfo classInfo = modelMapper.map(classInfoRepo.findById(classId).get(),ClassInfo.class);
        return classInfo;
    }

    public List<ClassInfo> getClassesByGradeId(int gradeId) {
        List<ClassInfo> classListByGradeId = classInfoRepo.getClassesByGradeId(gradeId);
        System.out.println(classListByGradeId);
        return classListByGradeId;
    }
}
