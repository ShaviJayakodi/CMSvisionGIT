package com.example.Vision.CMSvision.controller;

import com.example.Vision.CMSvision.dto.ClassFeeWithdrawDTO;
import com.example.Vision.CMSvision.service.CashWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cashWithdraw")
public class ClassFeeWithdrawController {
    @Autowired
    private CashWithdrawService withdrawService;

    @PostMapping("/withdraw")
    public ResponseEntity<?>cashWithdraw(@RequestBody ClassFeeWithdrawDTO withdrawDTO)
    {
        return withdrawService.newCashWithdraw(withdrawDTO);
    }




}
