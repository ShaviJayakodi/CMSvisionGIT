package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.ClassFeeDTO;
import com.example.Vision.CMSvision.dto.ClassMappingDTO;
import com.example.Vision.CMSvision.entity.*;
import com.example.Vision.CMSvision.repo.*;
import com.example.Vision.CMSvision.enums.statusValue;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ClassFeeDTO addPayment(ClassFeeDTO classFeeDTO) {
        ClassInfo classInfo = modelMapper.map(classInfoRepo.findById(classFeeDTO.getClassId()).get(), ClassInfo.class);
        Teacher teacher = modelMapper.map(teacherRepo.findById(classInfo.getTeacher().getTeacherId()).get(), Teacher.class);
        ClassFee checkClassFee = classFeeRepo.checkClassFee(classFeeDTO.getStudentId(), classFeeDTO.getMonth(), classFeeDTO.getYear(), classFeeDTO.getClassId());
        Student student = modelMapper.map(studentRepo.findById(classFeeDTO.getStudentId()).get(), Student.class);
        ClassFeeDTO dto = new ClassFeeDTO();
        ClassFee classFee = new ClassFee();
        System.out.println(checkClassFee);
        if (checkClassFee != null) {

            System.out.println("Paid");
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
        return dto;
    }


    public List<ClassFee> getClassFeesByClassIdToWithdraw(int classId)
    {
        List<ClassFee> classFeeList = classFeeRepo.activeClassFeeListToByClassId(classId,statusValue.ACTIVE.sts());
        return modelMapper.map(classFeeList,new TypeToken<List<ClassFee>>(){}.getType());
    }

}
