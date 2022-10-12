package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.ClassFeeDTO;
import com.example.Vision.CMSvision.dto.ClassMappingDTO;
import com.example.Vision.CMSvision.entity.ClassFee;
import com.example.Vision.CMSvision.entity.ClassMapping;
import com.example.Vision.CMSvision.service.ClassFeeService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/classFeeController")
public class ClassFeeController {

    @Autowired
    private ClassFeeService classFeeService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/getPayMethod")
    public ClassMapping getPayMethod(@RequestParam int studentId, @RequestParam  int classId)
    {
            return classFeeService.getPaymentMethod(studentId,classId);
    }
    @PostMapping("/addPayment")
    public ResponseEntity<?> addPayment (@RequestBody ClassFeeDTO classFeeDTO)
    {
        return classFeeService.addPayment(classFeeDTO);
    }

    @GetMapping("/getActiveClassFeeByClassId")
    public List<ClassFee> getActiveClassFeeByClassId(@RequestParam int classId)
    {
        return classFeeService.getClassFeesByClassIdToWithdraw(classId);
    }


    @PostMapping("/claimCash")
    public ClassFeeDTO getClassFeeId(@RequestParam int classFeeId)
    {
        return classFeeService.classFeeWithdraw(classFeeId);
    }
}
