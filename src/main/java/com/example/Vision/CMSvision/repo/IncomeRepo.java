package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income,Integer> {
}
