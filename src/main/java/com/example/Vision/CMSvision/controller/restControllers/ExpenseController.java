package com.example.Vision.CMSvision.controller.restControllers;

import com.example.Vision.CMSvision.dto.ExpenseDTO;
import com.example.Vision.CMSvision.entity.Expense;
import com.example.Vision.CMSvision.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addExpenseController")
@CrossOrigin
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @PostMapping("/addExpense")
    public ExpenseDTO addExpense(@RequestBody ExpenseDTO expenseDTO)
    {
        System.out.println(expenseDTO);
        return expenseService.addExpense(expenseDTO);

    }
    @GetMapping("/getAllExpense")
    public List<Expense> getAllExpense()
    {
        return expenseService.getALlExpense();
    }


}
