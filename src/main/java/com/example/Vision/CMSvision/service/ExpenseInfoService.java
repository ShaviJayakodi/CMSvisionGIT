package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.ExpenseInfoDTO;
import com.example.Vision.CMSvision.entity.ExpenseInfo;
import com.example.Vision.CMSvision.enums.statusValue;
import com.example.Vision.CMSvision.repo.ExpenseInfoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExpenseInfoService {
    @Autowired
    private ExpenseInfoRepo expenseInfoRepo;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ModelMapper modelMapper;

    public ExpenseInfoDTO addNewExpense(ExpenseInfoDTO expenseInfoDTO)
    {
        ExpenseInfo expenseInfo = new ExpenseInfo();
        if(expenseInfoDTO.getExpenseInfoId()>0) {

            expenseInfo.setExpenseInfoId(expenseInfoDTO.getExpenseInfoId());
            expenseInfo.setCode(expenseInfoDTO.getCode());
            expenseInfo.setDescription(expenseInfoDTO.getDescription());
            expenseInfo.setExpenseTitle(expenseInfoDTO.getExpenseTitle());
            expenseInfo.setStatus(statusValue.ACTIVE.sts());
        }
        else
        {
            int maxId = expenseInfoRepo.getMaxExpenseId();
            expenseInfo.setCode(commonService.generateCode(maxId));
            expenseInfo.setDescription(expenseInfoDTO.getDescription());
            expenseInfo.setExpenseTitle(expenseInfoDTO.getExpenseTitle());
            expenseInfo.setStatus(statusValue.ACTIVE.sts());
        }
        expenseInfoRepo.save(expenseInfo);
        ExpenseInfoDTO dto= modelMapper.map(expenseInfo,ExpenseInfoDTO.class);
        return dto;
    }

    public List<ExpenseInfo> getAllExpense()
    {
        List<ExpenseInfo> expenseList = expenseInfoRepo.findAll();
        return expenseList;
    }

    public String deleteExpense(int expenseId)
    {
        expenseInfoRepo.deleteById(expenseId);
      return "Delete Successful";
    }

    public ExpenseInfo getByExpenseId(int expenseInfoId)
    {
        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo = modelMapper.map(expenseInfoRepo.findById(expenseInfoId).get(),ExpenseInfo.class);
        return expenseInfo;
    }

}
