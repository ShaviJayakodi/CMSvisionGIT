package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.ClassFeeWithdrawDTO;

import com.example.Vision.CMSvision.entity.ClassFeeWithdraw;
import com.example.Vision.CMSvision.entity.Income;
import com.example.Vision.CMSvision.entity.Teacher;
import com.example.Vision.CMSvision.repo.ClassFeeWithdrawRepo;
import com.example.Vision.CMSvision.repo.IncomeRepo;
import com.example.Vision.CMSvision.repo.StudentRepo;
import com.example.Vision.CMSvision.repo.TeacherRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class CashWithdrawService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClassFeeWithdrawRepo withdrawRepo;

    @Autowired
    private IncomeRepo incomeRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    public ResponseEntity newCashWithdraw (ClassFeeWithdrawDTO withdrawDTO)
    {
        ClassFeeWithdraw withdraw = new ClassFeeWithdraw();
        Teacher teacher = modelMapper.map(teacherRepo.findById(withdrawDTO.getTeacherId()).get(),Teacher.class);

        //withdraw table
        withdraw.setFullAmount(withdrawDTO.getFullAmount());
        withdraw.setCommission(withdrawDTO.getCommission());
        withdraw.setTeacherAmount(withdrawDTO.getTeacherAmount());
        withdraw.setWithdrawDate(new Date());
        withdraw.setTeacher(teacher);

        //Income
        Income income = new Income();
        income.setWithdraw(withdraw);
        income.setAmount(withdraw.getCommission());
        income.setReceivedDate(new Date());


        withdrawRepo.save(withdraw);
        incomeRepo.save(income);

        ClassFeeWithdrawDTO dto = modelMapper.map(withdraw,ClassFeeWithdrawDTO.class);
        return ResponseEntity.ok(dto);
    }
}
