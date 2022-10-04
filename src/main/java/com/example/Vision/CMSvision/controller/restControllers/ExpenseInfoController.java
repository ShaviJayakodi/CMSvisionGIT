package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.ExpenseInfoDTO;
import com.example.Vision.CMSvision.entity.ExpenseInfo;
import com.example.Vision.CMSvision.service.ExpenseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/expenseController")
public class ExpenseInfoController {

    @Autowired
    private ExpenseInfoService expenseInfoService;

    @PostMapping("/saveExpense")
    public ExpenseInfoDTO addNewExpense(@RequestBody ExpenseInfoDTO expenseInfoDTO)
    {
        return expenseInfoService.addNewExpense(expenseInfoDTO);
    }

    @GetMapping("/getAllExpense")
    public List<ExpenseInfo> getAllExpense()
    {
        return expenseInfoService.getAllExpense();
    }

    @DeleteMapping("/deleteExpense")
    public String deleteExpense(int expenseInfoId)
    {
        return expenseInfoService.deleteExpense(expenseInfoId);
    }

    @GetMapping("/getByExpenseId")
    public ExpenseInfo getByExpenseId(@RequestParam int expenseInfoId)
    {
        return expenseInfoService.getByExpenseId(expenseInfoId);
    }

}
