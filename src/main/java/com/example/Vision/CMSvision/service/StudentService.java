package com.example.Vision.CMSvision.service;


import com.example.Vision.CMSvision.dto.StudentDTO;
import com.example.Vision.CMSvision.entity.Grade;
import com.example.Vision.CMSvision.entity.Student;
import com.example.Vision.CMSvision.repo.GradeRepo;
import com.example.Vision.CMSvision.repo.StudentRepo;
import com.example.Vision.CMSvision.enums.statusValue;
import com.example.Vision.CMSvision.enums.stakeHolderValues;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private GradeRepo gradeRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommonService commonService;

    @Transactional
      public ResponseEntity saveNewStudent(StudentDTO studentDTO) {
        int maxId =studentRepo.getMaxGradeId();
        StudentDTO dto = new StudentDTO();
        try {
            Student student = new Student();
            Grade grade =modelMapper.map(gradeRepo.findById(studentDTO.getGradeId()).get(),Grade.class);
            if(studentDTO.getStudentId()>0)
            {
                student.setStudentId(studentDTO.getStudentId());
                student.setRegNo(studentDTO.getRegNo());
                student.setFirstName(studentDTO.getFirstName());
                student.setLastName(studentDTO.getLastName());
                student.setAddress(studentDTO.getAddress());
                student.setSchool(studentDTO.getSchool());
                student.setAttendDate(studentDTO.getAttendDate());
                student.setBirthDay(studentDTO.getBirthDay());
                student.setGrade(grade);
                student.setGender(studentDTO.getGender());
                student.setParentName(studentDTO.getParentName());
                student.setRelationship(studentDTO.getRelationship());
                student.setMob1(studentDTO.getMob1());
                student.setMob2(studentDTO.getMob2());
                student.setStatus(statusValue.ACTIVE.sts());
            }
            else
            {

                student.setFirstName(studentDTO.getFirstName());
                student.setLastName(studentDTO.getLastName());
                student.setAddress(studentDTO.getAddress());
                student.setSchool(studentDTO.getSchool());
                student.setAttendDate(studentDTO.getAttendDate());
                student.setBirthDay(studentDTO.getBirthDay());
                student.setGrade(grade);
                student.setRegNo(commonService.genarateRegNo(maxId,stakeHolderValues.STUDENT.code()));
                student.setGender(studentDTO.getGender());
                student.setParentName(studentDTO.getParentName());
                student.setRelationship(studentDTO.getRelationship());
                student.setMob1(studentDTO.getMob1());
                student.setMob2(studentDTO.getMob2());
                student.setStatus(statusValue.ACTIVE.sts());
            }


            //System.out.println(student);
            student =  studentRepo.save(student);
            dto = modelMapper.map(student, StudentDTO.class);
        }
        catch (Exception exception)
        {
            System.out.println("something went wrong"+exception);
        }

        return ResponseEntity.ok(dto);
    }


    public List<StudentDTO>   getAllStudent()
    {
        List<StudentDTO> studentList = modelMapper.map(studentRepo.findAll(),new TypeToken<List<Student>>(){}.getType());
        return studentList;
    }

    public Student getUniqueById(int studentId)
    {
        Student student= modelMapper.map(studentRepo.findById(studentId).get(),Student.class);
        return student;
    }

    public Student getUniqueByRegNo(int regNo)
    {
        Student student = studentRepo.getUniqueStudentByRegNo(regNo);
        return student;
    }

    public String deleteById(int studentId)
    {
        studentRepo.deleteById(studentId);
        return "Successfully Deleted";
    }

    public List<Student> getStudentByGradeId(int gradeId)
    {
        List<Student> students = studentRepo.getStudentsByGradeId(gradeId);
        return students;
    }



}
