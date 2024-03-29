package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.ExpenseDTO;
import com.example.Vision.CMSvision.entity.Expense;
import com.example.Vision.CMSvision.entity.ExpenseInfo;
import com.example.Vision.CMSvision.entity.MarkAttendance;
import com.example.Vision.CMSvision.repo.ExpenseInfoRepo;
import com.example.Vision.CMSvision.enums.statusValue;
import com.example.Vision.CMSvision.repo.ExpenseRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;
    @Autowired
    private ExpenseInfoRepo expenseInfoRepo;
    @Autowired
    private ModelMapper modelMapper;
    public ExpenseDTO addExpense(ExpenseDTO expenseDTO)
    {
        Expense expense = new Expense();
        ExpenseInfo expenseInfo = modelMapper.map(expenseInfoRepo.findById(expenseDTO.getExpenseInfoId()).get(),ExpenseInfo.class);

        expense.setDescription(expenseDTO.getDescription());
        expense.setStatus(statusValue.ACTIVE.sts());
        expense.setValue(expenseDTO.getValue());
        expense.setExpenseInfo(expenseInfo);
        expense.setDate(expenseDTO.getDate());
        expenseRepo.save(expense);

        ExpenseDTO dto= modelMapper.map(expense,ExpenseDTO.class);
        return dto;
    }

    public List<Expense> getALlExpense()
    {
        List<Expense> expenseList = expenseRepo.findAll();
        return modelMapper.map(expenseList,new TypeToken<List<Expense>>(){}.getType());

    }

}
