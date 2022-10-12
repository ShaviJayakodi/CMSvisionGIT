package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.MarkAttendanceDTO;
import com.example.Vision.CMSvision.dto.OpenClassDTO;
import com.example.Vision.CMSvision.entity.*;
import com.example.Vision.CMSvision.enums.statusValue;
import com.example.Vision.CMSvision.repo.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MarkAttendanceService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ClassInfoRepo classInfoRepo;
    @Autowired
    private OpenClassRepo openClassRepo;
    @Autowired
    private MarkAttendnaceRepo markAttendnaceRepo;
    @Autowired
    private ClassMappingRepo classMappingRepo;
    @Autowired
    private ModelMapper modelMapper;
    //attendance
  /*  public MarkAttendanceDTO getOpenedClassesForStudent(int studentId,int openClassId)
    {
        Student student = modelMapper.map(studentRepo.findById(studentId).get(),Student.class);

        OpenClass openClass = modelMapper.map(openClassRepo.findById(openClassId).get(),OpenClass.class);
        ClassInfo classInfo = modelMapper.map(classInfoRepo.findById(openClass.getClassInfo().getClassId()).get(),ClassInfo.class);
        MarkAttendanceDTO markAttendanceDTO = new MarkAttendanceDTO();
        MarkAttendance mark = new MarkAttendance();
        Date classDate = openClass.getOpenDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dDate = new Date();
        System.out.println(classDate);
        int classId = classInfo.getClassId();

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        MarkAttendance student1=  markAttendnaceRepo.getbyStudentClassAndOpen(studentId,classId,openClassId);
        System.out.println(student1);

        Date date = student1.getAttendanceDate();
        String new1 =classDate.toString();
        String new2= date.toString();

        System.out.println(new1+"    "+new2);
        markAttendanceDTO = modelMapper.map(student1,MarkAttendanceDTO.class);
            String status ="";

            if(new1.equals(new2))
        {
            System.out.println("true");
        }
        else
        {
            System.out.println("false");
        }

        return markAttendanceDTO;
    }
*/

    public List<MarkAttendanceDTO> getAll()
    {
       List<MarkAttendance> list =markAttendnaceRepo.findAll();
       return modelMapper.map(list, new TypeToken<List<MarkAttendanceDTO>>(){}.getType());

    }

    public List<OpenClass> getOpenedClassesForStudent(int regNo)
    {

        List<OpenClass> showOpenClassForStudent= new ArrayList<>();

        OpenClassDTO openClassDTO =new OpenClassDTO();
        Student student = studentRepo.getUniqueStudentByRegNo(regNo);
        List<ClassMapping> classMappingList = classMappingRepo.getClassMappingByStudentId(student.getStudentId());
        List<OpenClass> openClassList = openClassRepo.findActiveClasses(statusValue.ACTIVE.sts());
        for (OpenClass openClass : openClassList)
        {
            for (ClassMapping classMapping :classMappingList)
            {
                //System.out.println(openClass.getClassInfo().getClassId()+" "+classMapping.getClassInfo().getClassId());
                if(openClass.getClassInfo().getClassId()==classMapping.getClassInfo().getClassId())
                {
                    OpenClass oClass = new OpenClass();
                    int openClassId=openClass.getOpenClassId();
                    Date openDate = openClass.getOpenDate();
                    int status = openClass.getStatus();


                    int classId = openClass.getClassInfo().getClassId();
                    oClass.setClassInfo(openClass.getClassInfo());
                    oClass.setOpenDate(openClass.getOpenDate());
                    oClass.setStatus(openClass.getStatus());
                    oClass.setOpenClassId(openClass.getOpenClassId());
                    showOpenClassForStudent.add(modelMapper.map(oClass,OpenClass.class));
                    System.out.println(openClassId+" || "+openDate+" || "+status+" || "+classId);

                }
            }

        }

        System.out.println(showOpenClassForStudent);
        return showOpenClassForStudent;
    }

    public ResponseEntity addAttendance(MarkAttendanceDTO markDTO) {
        String message = "";
        // ClassInfo classInfo = modelMapper.map(classInfoRepo.findById(markDTO.getClassInfoId()).get(),ClassInfo.class);
        Student student = modelMapper.map(studentRepo.findById(markDTO.getStudentId()).get(), Student.class);
        OpenClass openClass = modelMapper.map(openClassRepo.findById(markDTO.getOpenClassId()).get(), OpenClass.class);
        List<MarkAttendance> list = markAttendnaceRepo.getAllAttendanceByStudentId(markDTO.getStudentId());
        MarkAttendanceDTO markAttendanceDTO;
        MarkAttendance markAttendance = new MarkAttendance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
/*formatter = new SimpleDateFormat("yyyy-MM-dd");
	strDate = formatter.format(date);
	System.out.println("Date Format with dd-M-yyyy : "+strDate);
*/
        //already attend
        /*  if(markDTO.getAttendanceDate().equals(list.))*/

            /* if(markDTO.getStudentId()==markAttendance1.getStudent().getStudentId())
            {
               if(markDTO.getOpenClassId()==markAttendance1.getOpenClass().getOpenClassId())
                {

                    if(markAttendance1.getAttendanceId()>0)
                    {
                      message="already attend";
                    }
                    else
                    {
                        message="";
                    }
                }message="";
            }
            message="";
*/
         /*   if (markDTO.getOpenClassId() == markAttendance1.getOpenClass().getOpenClassId()) {
                if (markAttendance1.getAttendanceDate() == markDTO.getAttendanceDate()) {
                    //message="Already Added";


                } else {
                    message = "";
                }


            }

            //not attend yet
            if (message == "" || message == null) {

                markAttendance.setAttendanceDate(markDTO.getAttendanceDate());
                markAttendance.setStudent(student);
                markAttendance.setClassInfo(openClass.getClassInfo());
                markAttendance.setOpenClass(openClass);
                markAttendnaceRepo.save(markAttendance);
            }
            else
            {
                return modelMapper.map(markAttendance,MarkAttendanceDTO.class);
            }
        }
*/
            String strDate = fmt.format(markDTO.getAttendanceDate());
            List<MarkAttendance> mrkAttDTO = markAttendnaceRepo.checkAttendance(student.getStudentId(),openClass.getClassInfo().getClassId(),strDate,openClass.getOpenClassId());
           /* if(strDate.equals(listAttDate)&&markAttendance1.getOpenClass().getOpenClassId()==openClass.getOpenClassId()&&markAttendance1.getClassInfo().getClassId()==openClass.getClassInfo().getClassId())
            {
                System.out.println("already Attednded");
            }
            else
            {
                System.out.println("not");
                markAttendance.setAttendanceDate(markDTO.getAttendanceDate());
                markAttendance.setStudent(student);
                markAttendance.setClassInfo(openClass.getClassInfo());
                markAttendance.setOpenClass(openClass);
                markAttendnaceRepo.save(markAttendance);
            }*/
            if(mrkAttDTO.size()>0)
            {
                System.out.println("already");
                return ResponseEntity.notFound().build();
            }
            else
            {
                System.out.println("not");
                markAttendance.setAttendanceDate(markDTO.getAttendanceDate());
                markAttendance.setStudent(student);
                markAttendance.setClassInfo(openClass.getClassInfo());
                markAttendance.setOpenClass(openClass);
                markAttendnaceRepo.save(markAttendance);
            }

            markAttendanceDTO = modelMapper.map(markAttendance, MarkAttendanceDTO.class);
            return ResponseEntity.ok( markAttendanceDTO);
    }



   public List<MarkAttendance>  getAttendanceReviewForStudentId(int studentId,int classId,Date fromDate,Date toDate)
    {
        List<ClassMapping> classMappedList = classMappingRepo.getAttendanceReviewForStudentId(studentId);
        System.out.println(classMappedList);
        System.out.println(studentId+" "+classId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");


            List<OpenClass> openClassList = openClassRepo.getOpenClassByClassInfoClassId(classId,fromDate,toDate);
            System.out.println(openClassList);
            List<Integer> collect = openClassList.stream().map(x -> x.getOpenClassId()).collect(Collectors.toList());
            List<MarkAttendance> markAttendances = markAttendnaceRepo.getMarkAttendance(studentId,collect);
            System.out.println(markAttendances);
            /* for (OpenClass openClass : openClassList)
            {
                System.out.println(openClass.getClassInfo().getClassId()+" || "+openClass.getOpenDate()+" || "+ openClass.getOpenClassId());

            }*/
        System.out.println(collect);


        return markAttendances;
    }






}
