package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.ClassMappingDTO;
import com.example.Vision.CMSvision.dto.MarkAttendanceDTO;
import com.example.Vision.CMSvision.dto.OpenClassDTO;
import com.example.Vision.CMSvision.entity.ClassInfo;
import com.example.Vision.CMSvision.entity.ClassMapping;
import com.example.Vision.CMSvision.entity.OpenClass;
import com.example.Vision.CMSvision.entity.Student;
import com.example.Vision.CMSvision.enums.statusValue;
import com.example.Vision.CMSvision.repo.ClassInfoRepo;
import com.example.Vision.CMSvision.repo.ClassMappingRepo;
import com.example.Vision.CMSvision.repo.OpenClassRepo;
import com.example.Vision.CMSvision.repo.StudentRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OpenClassService {
    @Autowired
    private OpenClassRepo openClassRepo;
    @Autowired
    private ClassInfoRepo classInfoRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClassMappingRepo classMappingRepo;
    @Autowired
    private StudentRepo studentRepo;
    public OpenClassDTO addOpenClass(OpenClassDTO openClassDTO)
    {
        OpenClassDTO dto;
        OpenClass newClass = new  OpenClass();
        ClassInfo classInfo= modelMapper.map(classInfoRepo.findById(openClassDTO.getClassInfoId()).get(),ClassInfo.class);


        if(openClassDTO.getOpenClassId()>0)
        {
            newClass.setOpenClassId(openClassDTO.getOpenClassId());
            newClass.setOpenDate(openClassDTO.getOpenDate());
            newClass.setClassInfo(classInfo);
            newClass.setStatus(statusValue.DEACTIVE.sts());
        }
        else
        {
            newClass.setClassInfo(classInfo);
            newClass.setStatus(statusValue.ACTIVE.sts());
            newClass.setOpenDate(openClassDTO.getOpenDate());
        }
        newClass = openClassRepo.save(newClass);
        dto=modelMapper.map(newClass,OpenClassDTO.class);
        return dto;
    }

    public List<OpenClass> getAllOpenedClass()
    { int active =1,diactive=2;

       List<OpenClass> openedClassList =  openClassRepo.findActiveClasses(active);
       return openedClassList;
    }

    public OpenClass openClassGetById(int openCLassId)
    {
        OpenClass openClass = modelMapper.map(openClassRepo.findById(openCLassId).get(),OpenClass.class);
        return openClass;
    }




   /* public List<OpenClass> save(int regNo)
    {
        List<OpenClass> showOpenClassForStudent= new ArrayList<>();
        OpenClass oClass = new OpenClass();
        OpenClassDTO openClassDTO =new OpenClassDTO();
        Student student = studentRepo.getUniqueStudentByRegNo(regNo);
        List<ClassMapping> classMappingList = classMappingRepo.getClassMappingByStudentId(student.getStudentId());
        List<OpenClass> openClassList = openClassRepo.findActiveClasses(statusValue.ACTIVE.sts());
        for (OpenClass openClass : openClassList)
        {
            for (ClassMapping classMapping :classMappingList)
            {
                System.out.println(openClass.getClassInfo().getClassId()+" "+classMapping.getClassInfo().getClassId());
                if(openClass.getClassInfo().getClassId()==classMapping.getClassInfo().getClassId())
                {
                    int openClassId=openClass.getOpenClassId();
                    Date openDate = openClass.getOpenDate();
                    int status = openClass.getStatus();


                    int classId = openClass.getClassInfo().getClassId();
                    oClass.setClassInfo(openClass.getClassInfo());
                    oClass.setOpenDate(openClass.getOpenDate());
                    oClass.setStatus(openClass.getStatus());
                    oClass.setOpenClassId(openClass.getOpenClassId());
                    showOpenClassForStudent.add(modelMapper.map(oClass,OpenClass.class));
                    System.out.println(openClassId+" "+openDate+" "+status+" "+classId);

                }
            }
        }
        return showOpenClassForStudent;
    }*/
}


