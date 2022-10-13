package com.example.Vision.CMSvision.controller.viewControllers;

import com.example.Vision.CMSvision.dto.StudentDTO;
import com.example.Vision.CMSvision.entity.Student;
import com.example.Vision.CMSvision.entity.User;
import com.example.Vision.CMSvision.repo.StudentRepo;
import com.example.Vision.CMSvision.service.loginService;
import org.hibernate.loader.collection.OneToManyJoinWalker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class otherController {

    @Autowired
    private com.example.Vision.CMSvision.service.loginService loginService;


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
    public String loadStudentMain(HttpSession session)
    {
        return sessionValue(session,"/studentMain");

    }

    @RequestMapping(value = "/loadStudentRegistration", method = RequestMethod.GET)
    public String loadStudentRegistration(HttpSession session)
    {
        return sessionValue(session,"/studentRegistration");

    }

    @RequestMapping(value = "/loadStudentUpdate", method = RequestMethod.GET)
    public String loadStudentUpdate(HttpSession session)
    {
        return sessionValue(session,"/studentUpdate");

    }

    @RequestMapping(value = "/loadStudentDelete" , method = RequestMethod.GET)
    public String loadStudentDelete(HttpSession session)
    {
        return sessionValue(session,"/studentDelete");

    }

    @RequestMapping(value = "/loadStudentInquiry", method = RequestMethod.GET)
    public String loadStudentInquiry(HttpSession session)
    {
        return sessionValue(session,"/studentInquiry");

    }


    //Teacher Mapping
    @RequestMapping(value = "/loadTeacherMain", method = RequestMethod.GET)
    public String loadTeacherMain(HttpSession session)
    {
        return sessionValue(session,"/teacherMain");

    }

    @RequestMapping(value = "/loadTeacherRegistration" , method = RequestMethod.GET)
    public String loadTeacherRegistration(HttpSession session)
    {
        return sessionValue(session,"/teacherRegistration");

    }
    @RequestMapping(value = "/loadTeacherUpdate" , method = RequestMethod.GET)
    public String loadTeacherUpdate(HttpSession session)
    {
        return sessionValue(session,"/teacherUpdate");

    }

    @RequestMapping(value = "/loadTeacherDelete" , method = RequestMethod.GET)
    public String loadTeacherDelete(HttpSession session)
    {
        return sessionValue(session,"/teacherDelete");

    }

    @RequestMapping(value = "/loadTeacherInquiry" , method = RequestMethod.GET)
    public String loadTeacherInquiry(HttpSession session)
    {
        return sessionValue(session,"/teacherInquiry");

    }


    //Officer Mapping
    @RequestMapping(value = "/loadOfficerMain", method = RequestMethod.GET)
    public String loadOfficerMain(HttpSession session)
    {
        return sessionValue(session,"/officerMain");

    }

    @RequestMapping(value = "/loadOfficerRegistration",method = RequestMethod.GET)
    public String loadOfficerRegistration(HttpSession session)
    {
        return sessionValue(session,"/officerRegistration");

    }

    @RequestMapping(value = "/loadOfficerUpdate", method = RequestMethod.GET)
    public String loadOfficerUpdate(HttpSession session)
    {
        return sessionValue(session,"/officerUpdate");

    }

    @RequestMapping(value = "/loadOfficerDelete", method = RequestMethod.GET)
    public String loadDelete(HttpSession session)
    {
        return sessionValue(session,"/officerDelete");

    }

    @RequestMapping(value = "/loadOfficerInquiry" ,method = RequestMethod.GET)
    public String loadOfficerInquiry(HttpSession session)
    {
        return sessionValue(session,"/officerInquiry");

    }

    //Grade Mapping

    @RequestMapping(value = "/loadGradeMain",method = RequestMethod.GET)
    public String loadGradrMain(HttpSession session)
    {
        return sessionValue(session,"/gradeMain");

    }

    @RequestMapping(value = "/loadGradeRegistration" , method = RequestMethod.GET)
    public String loadGradeRegistration(HttpSession session)
    {
        return sessionValue(session,"/gradeRegistration");

    }

    @RequestMapping(value = "/loadGradeUpdate",method = RequestMethod.GET)
    public String loadGradeUpdate(HttpSession session)
    {
        return sessionValue(session,"/gradeUpdate");

    }

    @RequestMapping(value = "/loadGradeDelete",method = RequestMethod.GET)
    public String loadGradeDelete(HttpSession session)
    {
        return sessionValue(session,"/gradeDelete");

    }

    @RequestMapping(value = "loadGradeInquiry",method = RequestMethod.GET)
    public String loadGradeInquiry(HttpSession session)
    {
        return sessionValue(session,"/gradeInquiry");

    }


    //Subject Mapping

    @RequestMapping(value = "/loadSubjectMain",method = RequestMethod.GET)
    public String loadSubjectMain(HttpSession session)
    {
        return sessionValue(session,"/subjectMain");

    }

    @RequestMapping(value = "/loadSubjectRegistration",method = RequestMethod.GET)
    public String loadSubjectRegistration(HttpSession session)
    {
        return sessionValue(session,"/subjectRegistration");

    }

    @RequestMapping(value = "/loadSubjectUpdate",method = RequestMethod.GET)
    public String loadSubjectUpdate(HttpSession session)
    {
        return sessionValue(session,"/subjectUpdate");

    }

    @RequestMapping(value = "/loadSubjectDelete",method = RequestMethod.GET)
    public String loadSubjectDelete(HttpSession session)
    {
        return sessionValue(session,"/subjectDelete");

    }

    @RequestMapping(value = "/loadSubjectInquiry",method = RequestMethod.GET)
    public String loadSubjectInquiry(HttpSession session)
    {
        return sessionValue(session,"/subjectInquiry");

    }

    //Hall Mapping

    @RequestMapping(value = "/loadHallMain",method = RequestMethod.GET)
    public String loadHallMain(HttpSession session)
    {
        return sessionValue(session,"/hallMain");

    }

    @RequestMapping(value = "/loadHallRegistration",method = RequestMethod.GET)
    public String loadRegistration(HttpSession session)
    {
        return sessionValue(session,"/hallRegistration");

    }

    @RequestMapping(value = "/loadHallUpdate",method = RequestMethod.GET)
    public String loadHallUpdate(HttpSession session)
    {
        return sessionValue(session,"/hallUpdate");

    }

    @RequestMapping(value = "/loadHallDelete",method = RequestMethod.GET)
    public String loadHllDelete(HttpSession session)
    {
        return sessionValue(session,"/hallDelete");

    }

    @RequestMapping(value = "/loadHallInquiry",method = RequestMethod.GET)
    public String loadHallInquiry (HttpSession session)
    {
        return sessionValue(session,"/hallInquiry");

    }

    //Class Mapping
    @RequestMapping(value = "/loadClassMain",method = RequestMethod.GET)
    public String loadClassMain(HttpSession session)
    {
        return sessionValue(session,"/classMain");

    }

    @RequestMapping(value = "/loadClassRegistration",method = RequestMethod.GET)
    public String loadClassRegistration(HttpSession session)
    {
        return sessionValue(session,"/classRegistration");

    }

    @RequestMapping(value = "/loadClassUpdate",method = RequestMethod.GET)
    public String loadClassUpdate(HttpSession session)
    {
        return sessionValue(session,"/classUpdate");

    }

    @RequestMapping(value = "/loadClassDelete",method = RequestMethod.GET)
    public String loadClassDelete(HttpSession session)
    {
        return sessionValue(session,"/classDelete");

    }


    @RequestMapping(value = "/loadClassInquiry",method = RequestMethod.GET)
    public String loadClassInquiry(HttpSession session)
    {
        return sessionValue(session,"/classInquiry");

    }



    //Student Mapping to class

    @RequestMapping(value = "/loadMappingMain",method = RequestMethod.GET)
    public String loadClassMapping(HttpSession session)
    {
        return sessionValue(session,"/classMappingMain");

    }

    @RequestMapping(value = "/loadAddMapping", method = RequestMethod.GET)
    public String loadAddMapping(HttpSession session)
    {
        return sessionValue(session,"/addClassMapping");

    }

    @RequestMapping(value = "/loadClassMappingDetails",method = RequestMethod.GET)
    public String loadClassDetails(HttpSession session)
    {
        return sessionValue(session,"/classMappingDetails");

    }

    //Class open and close
    @RequestMapping(value = "/loadOpenClass",method = RequestMethod.GET)
    public String loadOpenClass(HttpSession session)
    {
        return sessionValue(session,"/classOpenClose");


    }

    //student attendance
    @RequestMapping(value = "/studentAttendance",method = RequestMethod.GET)
    public String loadAttendance(HttpSession session)
    {
        return sessionValue(session,"/studentAttendance");

    }


    //popup window
    @RequestMapping(value = "/loadPopupSearch",method = RequestMethod.GET)
    public String loadPopupSearchWindow(HttpSession session)
    {
        return sessionValue(session,"/popupSearch");

    }

    @RequestMapping(value = "/loadPopupTeacher",method = RequestMethod.GET)
    public String loadPopupTeacher(HttpSession session)
    {
        return sessionValue(session,"/popupTeacher");

    }

    //Student Attendance Review
    @RequestMapping(value = "/loadAttendanceReview",method = RequestMethod.GET)
    public String loadAttendanceReview(HttpSession session)
    {
        return sessionValue(session,"/attendanceReview");

    }

    //loadClassFee
    @RequestMapping(value = "/loadClassFee",method = RequestMethod.GET)
    public  String loadClassFee(HttpSession session)
    {
        return sessionValue(session,"/classFee");

    }

    //CLassFeeWithdraw
    @RequestMapping(value = "/loadClassFeeWithdraw",method = RequestMethod.GET)
    public String loadClassFeeWithdraw(HttpSession session)
    {
        return sessionValue(session,"/classFeeWithdraw");

    }


    //Expense Management
    @RequestMapping(value = "/loadExpenseInfoMain",method = RequestMethod.GET)
    public String loadExpenseInfoMain(HttpSession session)
    {
        return sessionValue(session,"/expenseInfoMain");

    }

    @RequestMapping(value = "/loadExpenseRegistration",method = RequestMethod.GET)
    public String loadExpenseRegistration(HttpSession session)
    {
        return sessionValue(session,"/expenseInfoRegistration");

    }

    @RequestMapping(value = "/loadExpenseUpdate",method = RequestMethod.GET)
    public String loadExpenseUpdate(HttpSession session)
    {
        return sessionValue(session,"/expenseUpdate");

    }

    @RequestMapping(value = "/loadExpenseDelete",method = RequestMethod.GET)
    public String loadExpenseDelete(HttpSession session)
    {
        return sessionValue(session,"/expenseDelete");

    }
    @RequestMapping(value = "/loadExpenseInquiry",method =RequestMethod.GET)
    public String loadExpenseInquiry(HttpSession session)
    {
        return sessionValue(session,"/expenseInquiry");

    }



    //Expense
    @RequestMapping(value = "/loadAddExpenseMain",method = RequestMethod.GET)
    public String loadAddExpenseMain(HttpSession session)
    {
        return sessionValue(session,"/addExpenseMain");

    }

    @RequestMapping(value = "/loadAddExpense",method = RequestMethod.GET)
    public String loadAddExpense(HttpSession session)
    {
        return sessionValue(session,"/addExpense");

    }

    @RequestMapping(value = "/loadAddExpenseDetails",method = RequestMethod.GET)
    public String loadAddExpenseDetails(HttpSession session)
    {
        return sessionValue(session,"/addExpenseDetails");

    }


    //User
    @RequestMapping(value = "/loadUserMain",method = RequestMethod.GET)
    public String loadUserMain(HttpSession session)
    {
        return sessionValue(session,"/userMain");

    }

    @RequestMapping(value = "/loadUserRegistration",method = RequestMethod.GET)
    public String loadUserRegistration(HttpSession session)
    {
        return sessionValue(session,"/userRegistration");

    }

    //Income
    @RequestMapping(value = "/loadIncomeDetails",method = RequestMethod.GET)
    public String loadIncomeDetails(HttpSession session)
    {
        return sessionValue(session,"/income");

    }

    @RequestMapping(value = "/loadAllIncomePopup",method = RequestMethod.GET)
    public String loadAllIncomePopup(HttpSession session)
    {
        return sessionValue(session,"/allIncome");

    }
    
    //login
    
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String loadLoginPage()
    {
        return "login";
    }

    //mainPage
    @RequestMapping(value = "/mainPage",method = RequestMethod.GET)
    public String loadMainPage(HttpSession session)
    {
        return sessionValue(session,"/mainPage");

    }

    public String sessionValue(HttpSession session, String page)
    {
        if(session.getAttribute("name")==null)
        {
            return "login";
        }
        else
            return page;
    }



    //login controllwe
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam String userName, @RequestParam String passWord, HttpServletRequest request)
    {

        System.out.println(userName+ " " + passWord);

        User user= loginService.login(userName,passWord);

        if(user!=null)
        {
            request.getSession().setAttribute("name",user);
            return "redirect:/mainPage";
        }
        else
            return "redirect:/";

    }

}
