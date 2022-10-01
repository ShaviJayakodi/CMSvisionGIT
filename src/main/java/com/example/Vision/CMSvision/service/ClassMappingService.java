package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.ClassMappingDTO;
import com.example.Vision.CMSvision.entity.ClassInfo;
import com.example.Vision.CMSvision.entity.ClassMapping;
import com.example.Vision.CMSvision.entity.Student;
import com.example.Vision.CMSvision.repo.ClassInfoRepo;
import com.example.Vision.CMSvision.repo.ClassMappingRepo;
import com.example.Vision.CMSvision.repo.StudentRepo;
import com.example.Vision.CMSvision.enums.statusValue;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClassMappingService {
    @Autowired
    private ClassInfoRepo classInfoRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ClassMappingRepo classMappingRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Transactional
    public ClassMappingDTO saveClassMap(ClassMappingDTO classMappingDTO)
    {
        ClassMappingDTO classMap = new ClassMappingDTO();
        ClassMapping classMapping = new ClassMapping();
        ClassInfo classInfo = modelMapper.map(classInfoRepo.findById(classMappingDTO.getClassInfoId()).get(),ClassInfo.class);
        Student student = modelMapper.map(studentRepo.findById(classMappingDTO.getStudentId()).get(),Student.class);

        /*Grade grade =modelMapper.map(gradeRepo.findById(studentDTO.getGradeId()).get(),Grade.class);*/

        if(classMappingDTO.getClassMappingId()>0)
        {

        }
        else
        {
            classMapping.setClassInfo(classInfo);
            classMapping.setStudent(student);
            classMapping.setClassYear(classMappingDTO.getClassYear());
            classMapping.setPaymentMethod(classMappingDTO.getPaymentMethod());
            classMapping.setAttendDate(classMappingDTO.getAttendDate());
            classMapping.setClassCode(classInfo.getClassCode());
            classMapping.setStatus(statusValue.ACTIVE.sts());

        }
        classMapping =classMappingRepo.save(classMapping);
        classMap =modelMapper.map(classMapping,ClassMappingDTO.class);

        System.out.println(classMappingDTO);
        return classMap;
    }

    public List<ClassMappingDTO> getMappingsByStudentId(int studentId)
    {
        List<ClassMapping> classMappings = classMappingRepo.getClassMappingByStudentId(studentId);
        List<ClassMappingDTO> classMappingDTOList = modelMapper.map(classMappings,new TypeToken<List<ClassMappingDTO>>(){}.getType());

        return classMappingDTOList;
    }


    public int deleteById(int mappingId) {
        return  classMappingRepo.deleteByClassMappingId(mappingId);
    }

    public ClassMapping getMappingsByMappingId(int mappingId) {

        ClassMapping classMapping = modelMapper.map(classMappingRepo.findById(mappingId).get(),ClassMapping.class);
        return classMapping;
    }
}

