package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.ClassFeeWithdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassFeeWithdrawRepo extends JpaRepository<ClassFeeWithdraw, Integer> {
}
