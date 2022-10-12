package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/incomeController")
@CrossOrigin
public class IncomeController {

    @Autowired
    private IncomeService incomeService;
    @GetMapping("/getIncomeData")
    public ResponseEntity<?>  getIncomeDetails(@RequestParam String fromDate, @RequestParam String toDate) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fDate = format.parse(fromDate);
        Date tDate = format.parse(toDate);
        return incomeService.getIncomeDetails(fDate,tDate);
    }

    @GetMapping("/getAllIncomesBetween2Days")
    public ResponseEntity<?> getAllIncomesBetweeen2Days (@RequestParam String fromDate, @RequestParam String toDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fDate = format.parse(fromDate);
        Date tDate = format.parse(toDate);
        return incomeService.getAllIncomesBetween2Days(fDate,tDate);
    }





}
