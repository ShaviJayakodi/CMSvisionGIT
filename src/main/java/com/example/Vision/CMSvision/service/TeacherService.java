package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.GradeDTO;
import com.example.Vision.CMSvision.dto.TeacherDTO;
import com.example.Vision.CMSvision.entity.Hall;
import com.example.Vision.CMSvision.entity.Teacher;
import com.example.Vision.CMSvision.repo.TeacherRepo;
import com.example.Vision.CMSvision.enums.stakeHolderValues;
import com.example.Vision.CMSvision.enums.statusValue;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommonService commonService;




    public ResponseEntity saveTeacher(TeacherDTO teacherDTO) {
        TeacherDTO teacher = new TeacherDTO();

        if (teacherDTO.getTeacherId() > 0)
        {

            teacher.setTeacherId(teacherDTO.getTeacherId());
            teacher.setFirstName(teacherDTO.getFirstName());
            teacher.setLastName(teacherDTO.getLastName());
            teacher.setBirthDay(teacherDTO.getBirthDay());
            teacher.setAttendDate(teacherDTO.getAttendDate());
            teacher.setAddress(teacherDTO.getAddress());
            teacher.setGender(teacherDTO.getGender());
            teacher.setMobNum1(teacherDTO.getMobNum1());
            teacher.setMobNum2(teacherDTO.getMobNum2());
            teacher.setEmailAddress(teacherDTO.getEmailAddress());
            teacher.setRegNO(teacherDTO.getRegNO());
            teacher.setCommission(teacherDTO.getCommission());
            teacher.setStatus(statusValue.ACTIVE.sts());
        }
        else
        {
            System.out.println(teacherDTO.getCommission());
            int maxId = teacherRepo.getMaxTeacherId();
            teacher.setFirstName(teacherDTO.getFirstName());
            teacher.setLastName(teacherDTO.getLastName());
            teacher.setBirthDay(teacherDTO.getBirthDay());
            teacher.setAttendDate(teacherDTO.getAttendDate());
            teacher.setAddress(teacherDTO.getAddress());
            teacher.setGender(teacherDTO.getGender());
            teacher.setMobNum1(teacherDTO.getMobNum1());
            teacher.setMobNum2(teacherDTO.getMobNum2());
            teacher.setEmailAddress(teacherDTO.getEmailAddress());
            teacher.setCommission(teacherDTO.getCommission());
            teacher.setRegNO(Integer.parseInt(commonService.genarateRegNo(maxId,stakeHolderValues.TEACHER.code())));
            teacher.setStatus(statusValue.ACTIVE.sts());

        }
         teacherRepo.save(modelMapper.map(teacher, Teacher.class));

        return ResponseEntity.ok(teacher);
    }



    public TeacherDTO getByRegNO (int regNo)
    {
        Teacher teacher = teacherRepo.getTeachersByRegNO(regNo);

        return modelMapper.map(teacher,TeacherDTO.class);
    }

    public Teacher findById(int teacherId)
    {
        Teacher teacher =modelMapper.map(teacherRepo.findById(teacherId).get(),Teacher.class);
        return teacher;
    }

    public List<TeacherDTO> findAllTeachers()
    {
        List<Teacher> teacherList = teacherRepo.findAll();
        return modelMapper.map(teacherList,new TypeToken<List<Teacher>>(){}.getType());

    }


    public String deleteByTeacherId(int teacherId)
    {
        String status="";
        if(teacherRepo.existsById(teacherId))
        {
            teacherRepo.deleteById(teacherId);
            status+="Teacher Deleted";
        }
        else
        {
            status+="Teacher Not Found!";
        }

        return status;

    }

}
