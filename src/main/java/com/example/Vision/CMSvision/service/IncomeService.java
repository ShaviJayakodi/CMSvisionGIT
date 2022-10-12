package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.entity.Expense;
import com.example.Vision.CMSvision.entity.Income;
import com.example.Vision.CMSvision.repo.ExpenseRepo;
import com.example.Vision.CMSvision.repo.IncomeRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IncomeService {
    @Autowired
    private IncomeRepo incomeRepo;
    @Autowired
    private ExpenseRepo expenseRepo;
    @Autowired
    private ModelMapper modelMapper;
    public ResponseEntity  getIncomeDetails(Date fromDate, Date toDate)
    {   double totalExpense=0;
        double totalIncome = 0;
        List<Double> array = new ArrayList<>();
        List<Income> incomeListBetween2Days = modelMapper.map(incomeRepo.getIncomeDetailBetween2Days(fromDate,toDate),new TypeToken<List<Income>>(){}.getType());
        List<Expense> expensesListBetween2Days = modelMapper.map(expenseRepo.getExpenseBetween2Days(fromDate,toDate),new TypeToken<List<Expense>>(){}.getType());
        for(Income income : incomeListBetween2Days)
        {
            totalIncome = totalIncome+income.getAmount();
        }
        for(Expense expense: expensesListBetween2Days)
        {
            totalExpense = totalExpense+expense.getValue();
        }
        double profit = totalIncome-totalExpense;
        array.add(totalIncome);
        array.add(totalExpense);
        array.add(profit);
        return ResponseEntity.ok(array);
    }

    public ResponseEntity getAllIncomesBetween2Days(Date fromDate, Date toDate)
    {
        List<Income> incomeListBetween2Days = modelMapper.map(incomeRepo.getIncomeDetailBetween2Days(fromDate,toDate),new TypeToken<List<Income>>(){}.getType());
        return ResponseEntity.ok(incomeListBetween2Days);
    }
}
