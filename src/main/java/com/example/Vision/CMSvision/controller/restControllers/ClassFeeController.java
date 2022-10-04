package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.ClassFeeDTO;
import com.example.Vision.CMSvision.dto.ClassMappingDTO;
import com.example.Vision.CMSvision.entity.ClassFee;
import com.example.Vision.CMSvision.entity.ClassMapping;
import com.example.Vision.CMSvision.service.ClassFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/classFeeController")
public class ClassFeeController {

    @Autowired
    private ClassFeeService classFeeService;
    @GetMapping("/getPayMethod")
    public ClassMapping getPayMethod(@RequestParam int studentId, @RequestParam  int classId)
    {
            return classFeeService.getPaymentMethod(studentId,classId);
    }
    @PostMapping("/addPayment")
    public ClassFeeDTO addPayment (@RequestBody ClassFeeDTO classFeeDTO)
    {
        return classFeeService.addPayment(classFeeDTO);
    }

    @GetMapping("/getActiveClassFeeByClassId")
    public List<ClassFee> getActiveClassFeeByClassId(@RequestParam int classId)
    {
        return classFeeService.getClassFeesByClassIdToWithdraw(classId);
    }
}
