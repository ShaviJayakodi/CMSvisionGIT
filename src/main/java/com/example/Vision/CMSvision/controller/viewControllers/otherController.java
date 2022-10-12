package com.example.Vision.CMSvision.controller.viewControllers;

import com.example.Vision.CMSvision.dto.StudentDTO;
import com.example.Vision.CMSvision.entity.Student;
import com.example.Vision.CMSvision.repo.StudentRepo;
import org.hibernate.loader.collection.OneToManyJoinWalker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class otherController {


    @RequestMapping(value="/loadStudentRegistration1",method=RequestMethod.GET)
    public String loadStudentegistration(Model model) {
        Student student = new Student();
        model.addAttribute("student",student);
        return "registration";
    }

    @RequestMapping(value = "/loadUser", method = RequestMethod.GET)
    public String loadUser()
    {
        return "user";
    }

    @RequestMapping(value = "/loadGrade", method = RequestMethod.GET)
    public String loadGrade()
    {
        return "gradeRegistration";
    }
/*
    @RequestMapping(value = "/loadGradeUpdate", method = RequestMethod.GET)
    public String loadGradeUpdate()
    {
        return "gradeUpdate";
    }*/

    //Student Mapping
    @RequestMapping(value = "/loadStudentMain", method =RequestMethod.GET)
    public String loadStudentMain()
    {
        return "studentMain";
    }

    @RequestMapping(value = "/loadStudentRegistration", method = RequestMethod.GET)
    public String loadStudentRegistration()
    {
        return "studentRegistration";
    }

    @RequestMapping(value = "/loadStudentUpdate", method = RequestMethod.GET)
    public String loadStudentUpdate()
    {
        return "studentUpdate";
    }

    @RequestMapping(value = "/loadStudentDelete" , method = RequestMethod.GET)
    public String loadStudentDelete()
    {
        return "studentDelete";
    }

    @RequestMapping(value = "/loadStudentInquiry", method = RequestMethod.GET)
    public String loadStudentInquiry()
    {
        return "studentInquiry";
    }


    //Teacher Mapping
    @RequestMapping(value = "/loadTeacherMain", method = RequestMethod.GET)
    public String loadTeacherMain()
    {
        return "teacherMain";
    }

    @RequestMapping(value = "/loadTeacherRegistration" , method = RequestMethod.GET)
    public String loadTeacherRegistration()
    {
        return "teacherRegistration";
    }
    @RequestMapping(value = "/loadTeacherUpdate" , method = RequestMethod.GET)
    public String loadTeacherUpdate()
    {
        return "teacherUpdate";
    }

    @RequestMapping(value = "/loadTeacherDelete" , method = RequestMethod.GET)
    public String loadTeacherDelete()
    {
        return "teacherDelete";
    }

    @RequestMapping(value = "/loadTeacherInquiry" , method = RequestMethod.GET)
    public String loadTeacherInquiry()
    {
        return "teacherInquiry";
    }


    //Officer Mapping
    @RequestMapping(value = "/loadOfficerMain", method = RequestMethod.GET)
    public String loadOfficerMain()
    {
        return "officerMain";
    }

    @RequestMapping(value = "/loadOfficerRegistration",method = RequestMethod.GET)
    public String loadOfficerRegistration()
    {
        return "officerRegistration";
    }

    @RequestMapping(value = "/loadOfficerUpdate", method = RequestMethod.GET)
    public String loadOfficerUpdate()
    {
        return "officerUpdate";
    }

    @RequestMapping(value = "/loadOfficerDelete", method = RequestMethod.GET)
    public String loadDelete()
    {
        return "officerDelete";
    }

    @RequestMapping(value = "/loadOfficerInquiry" ,method = RequestMethod.GET)
    public String loadOfficerInquiry()
    {
        return "officerInquiry";
    }

    //Grade Mapping

    @RequestMapping(value = "/loadGradeMain",method = RequestMethod.GET)
    public String loadGradrMain()
    {
        return "gradeMain";
    }

    @RequestMapping(value = "/loadGradeRegistration" , method = RequestMethod.GET)
    public String loadGradeRegistration()
    {
        return "gradeRegistration";
    }

    @RequestMapping(value = "/loadGradeUpdate",method = RequestMethod.GET)
    public String loadGradeUpdate()
    {
        return "gradeUpdate";
    }

    @RequestMapping(value = "/loadGradeDelete",method = RequestMethod.GET)
    public String loadGradeDelete()
    {
        return "gradeDelete";
    }

    @RequestMapping(value = "loadGradeInquiry",method = RequestMethod.GET)
    public String loadGradeInquiry()
    {
        return "gradeInquiry";
    }


    //Subject Mapping

    @RequestMapping(value = "/loadSubjectMain",method = RequestMethod.GET)
    public String loadSubjectMain()
    {
        return "subjectMain";
    }

    @RequestMapping(value = "/loadSubjectRegistration",method = RequestMethod.GET)
    public String loadSubjectRegistration()
    {
        return "subjectRegistration";
    }

    @RequestMapping(value = "/loadSubjectUpdate",method = RequestMethod.GET)
    public String loadSubjectUpdate()
    {
        return "subjectUpdate";
    }

    @RequestMapping(value = "/loadSubjectDelete",method = RequestMethod.GET)
    public String loadSubjectDelete()
    {
        return "subjectDelete";
    }

    @RequestMapping(value = "/loadSubjectInquiry",method = RequestMethod.GET)
    public String loadSubjectInquiry()
    {
        return "subjectInquiry";
    }

    //Hall Mapping

    @RequestMapping(value = "/loadHallMain",method = RequestMethod.GET)
    public String loadHallMain()
    {
        return "hallMain";
    }

    @RequestMapping(value = "/loadHallRegistration",method = RequestMethod.GET)
    public String loadRegistration()
    {
        return "hallRegistration";
    }

    @RequestMapping(value = "/loadHallUpdate",method = RequestMethod.GET)
    public String loadHallUpdate()
    {
        return "hallUpdate";
    }

    @RequestMapping(value = "/loadHallDelete",method = RequestMethod.GET)
    public String loadHllDelete()
    {
        return "hallDelete";
    }

    @RequestMapping(value = "/loadHallInquiry",method = RequestMethod.GET)
    public String loadHallInquiry ()
    {
        return "hallInquiry";
    }

    //Class Mapping
    @RequestMapping(value = "/loadClassMain",method = RequestMethod.GET)
    public String loadClassMain()
    {
        return "classMain";
    }

    @RequestMapping(value = "/loadClassRegistration",method = RequestMethod.GET)
    public String loadClassRegistration()
    {
        return "classRegistration";
    }

    @RequestMapping(value = "/loadClassUpdate",method = RequestMethod.GET)
    public String loadClassUpdate()
    {
        return "classUpdate";
    }

    @RequestMapping(value = "/loadClassDelete",method = RequestMethod.GET)
    public String loadClassDelete()
    {
        return "classDelete";
    }


    @RequestMapping(value = "/loadClassInquiry",method = RequestMethod.GET)
    public String loadClassInquiry()
    {
        return "/classInquiry";
    }



    //Student Mapping to class

    @RequestMapping(value = "/loadMappingMain",method = RequestMethod.GET)
    public String loadClassMapping()
    {
        return "/classMappingMain";
    }

    @RequestMapping(value = "/loadAddMapping", method = RequestMethod.GET)
    public String loadAddMapping()
    {
        return "/addClassMapping";
    }

    @RequestMapping(value = "/loadClassMappingDetails",method = RequestMethod.GET)
    public String loadClassDetails()
    {
        return "/classMappingDetails";
    }

    //Class open and close
    @RequestMapping(value = "/loadOpenClass",method = RequestMethod.GET)
    public String loadOpenClass()
    {
        return "/classOpenClose";

    }

    //student attendance
    @RequestMapping(value = "/studentAttendance",method = RequestMethod.GET)
    public String loadAttendance()
    {
        return "/studentAttendance";
    }


    //popup window
    @RequestMapping(value = "/loadPopupSearch",method = RequestMethod.GET)
    public String loadPopupSearchWindow()
    {
        return "/popupSearch";
    }

    @RequestMapping(value = "/loadPopupTeacher",method = RequestMethod.GET)
    public String loadPopupTeacher()
    {
        return "/popupTeacher";
    }

    //Student Attendance Review
    @RequestMapping(value = "/loadAttendanceReview",method = RequestMethod.GET)
    public String loadAttendanceReview()
    {
        return "/attendanceReview";
    }

    //loadClassFee
    @RequestMapping(value = "/loadClassFee",method = RequestMethod.GET)
    public  String loadClassFee()
    {
        return "/classFee";
    }

    //CLassFeeWithdraw
    @RequestMapping(value = "/loadClassFeeWithdraw",method = RequestMethod.GET)
    public String loadClassFeeWithdraw()
    {
        return "/classFeeWithdraw";
    }


    //Expense Management
    @RequestMapping(value = "/loadExpenseInfoMain",method = RequestMethod.GET)
    public String loadExpenseInfoMain()
    {
        return "/expenseInfoMain";
    }

    @RequestMapping(value = "/loadExpenseRegistration",method = RequestMethod.GET)
    public String loadExpenseRegistration()
    {
        return "/expenseInfoRegistration";
    }

    @RequestMapping(value = "/loadExpenseUpdate",method = RequestMethod.GET)
    public String loadExpenseUpdate()
    {
        return "/expenseUpdate";
    }

    @RequestMapping(value = "/loadExpenseDelete",method = RequestMethod.GET)
    public String loadExpenseDelete()
    {
        return "/expenseDelete";
    }
    @RequestMapping(value = "/loadExpenseInquiry",method =RequestMethod.GET)
    public String loadExpenseInquiry()
    {
        return "/expenseInquiry";
    }



    //Expense
    @RequestMapping(value = "/loadAddExpenseMain",method = RequestMethod.GET)
    public String loadAddExpenseMain()
    {
        return "/addExpenseMain";
    }

    @RequestMapping(value = "/loadAddExpense",method = RequestMethod.GET)
    public String loadAddExpense()
    {
        return "/addExpense";
    }

    @RequestMapping(value = "/loadAddExpenseDetails",method = RequestMethod.GET)
    public String loadAddExpenseDetails()
    {
        return "/addExpenseDetails";
    }


    //User
    @RequestMapping(value = "/loadUserMain",method = RequestMethod.GET)
    public String loadUserMain()
    {
        return "/userMain";
    }

    @RequestMapping(value = "/loadUserRegistration",method = RequestMethod.GET)
    public String loadUserRegistration()
    {
        return "/userRegistration";
    }

    //Income
    @RequestMapping(value = "/loadIncomeDetails",method = RequestMethod.GET)
    public String loadIncomeDetails()
    {
        return "/income";
    }

    @RequestMapping(value = "/loadAllIncomePopup",method = RequestMethod.GET)
    public String loadAllIncomePopup()
    {
        return "/allIncome";
    }
    
    //login
    
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loadLoginPage()
    {
        return "login";
    }

    //mainPage
    @RequestMapping(value = "/mainPage",method = RequestMethod.GET)
    public String loadMainPage()
    {
        return "mainPage";
    }

}
