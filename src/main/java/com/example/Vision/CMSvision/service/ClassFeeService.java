package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.ClassFeeDTO;
import com.example.Vision.CMSvision.dto.ClassMappingDTO;
import com.example.Vision.CMSvision.entity.*;
import com.example.Vision.CMSvision.repo.*;
import com.example.Vision.CMSvision.enums.statusValue;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClassFeeService {

    @Autowired
    private ClassMappingRepo classMappingRepo;
    @Autowired
    private ClassFeeRepo classFeeRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClassInfoRepo classInfoRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;

    public ClassMapping getPaymentMethod(int studentId, int classId) {
        ClassMapping classMapping = modelMapper.map(classMappingRepo.getPaymentMethod(studentId, classId), ClassMapping.class);
        return classMapping;
    }

    public ResponseEntity addPayment(ClassFeeDTO classFeeDTO) {
        ClassInfo classInfo = modelMapper.map(classInfoRepo.findById(classFeeDTO.getClassId()).get(), ClassInfo.class);
        Teacher teacher = modelMapper.map(teacherRepo.findById(classInfo.getTeacher().getTeacherId()).get(), Teacher.class);
        ClassFee checkClassFee = classFeeRepo.checkClassFee(classFeeDTO.getStudentId(), classFeeDTO.getMonth(), classFeeDTO.getYear(), classFeeDTO.getClassId());
        Student student = modelMapper.map(studentRepo.findById(classFeeDTO.getStudentId()).get(), Student.class);
        ClassFeeDTO dto = new ClassFeeDTO();
        ClassFee classFee = new ClassFee();
        System.out.println(checkClassFee);
        if (checkClassFee != null) {

            return ResponseEntity.badRequest().build();

        } else {
            //int rl = statusValue.DEACTIVE.sts();
            if (classFeeDTO.getIsRelease() == 1) {
                classFee.setIsRelease(statusValue.ACTIVE.sts());
                classFee.setAuthorizedBy(classFeeDTO.getAuthorizedBy());
                classFee.setAmount(0);
            } else {
                classFee.setIsRelease(statusValue.DEACTIVE.sts());
                classFee.setAuthorizedBy(0);
                classFee.setAmount(classFeeDTO.getAmount());
            }
            classFee.setIsWithdraw(statusValue.ACTIVE.sts());
            classFee.setPayDate(classFeeDTO.getPayDate());
            classFee.setPayMethod(classFeeDTO.getPayMethod());
            classFee.setTeacherId(classInfo.getTeacher().getTeacherId());
            classFee.setYear(classFeeDTO.getYear());
            classFee.setClassInfo(classInfo);
            classFee.setStudent(student);
            classFee.setMonth(classFeeDTO.getMonth());
            classFeeRepo.save(classFee);


        }
        dto = modelMapper.map(classFee, ClassFeeDTO.class);
        return ResponseEntity.ok(dto);
    }


    public List<ClassFee> getClassFeesByClassIdToWithdraw(int classId)
    {
        List<ClassFee> classFeeList = classFeeRepo.activeClassFeeListToByClassId(classId,statusValue.ACTIVE.sts());
        return modelMapper.map(classFeeList,new TypeToken<List<ClassFee>>(){}.getType());
    }

    public ClassFeeDTO classFeeWithdraw(int classFeeId) {
        ClassFee classFee = modelMapper.map(classFeeRepo.findById(classFeeId).get(),ClassFee.class);
        Student student = modelMapper.map(studentRepo.findById(classFee.getStudent().getStudentId()).get(),Student.class);
        ClassInfo classInfo = modelMapper.map(classInfoRepo.findById(classFee.getClassInfo().getClassId()).get(),ClassInfo.class);

        ClassFee newClassFee = new ClassFee();

        newClassFee.setClassFeeId(classFee.getClassFeeId());
        newClassFee.setAmount(classFee.getAmount());
        newClassFee.setIsRelease(classFee.getIsRelease());
        newClassFee.setMonth(classFee.getMonth());
        newClassFee.setPayDate(classFee.getPayDate());
        newClassFee.setPayMethod(classFee.getPayMethod());
        newClassFee.setTeacherId(classFee.getTeacherId());
        newClassFee.setYear(classFee.getYear());
        newClassFee.setClassInfo(classInfo);
        newClassFee.setStudent(student);
        newClassFee.setAuthorizedBy(classFee.getAuthorizedBy());
        newClassFee.setIsWithdraw(statusValue.DEACTIVE.sts());

        classFeeRepo.save(newClassFee);
        ClassFeeDTO dto = modelMapper.map(newClassFee,ClassFeeDTO.class);
        return dto;
    }
}
