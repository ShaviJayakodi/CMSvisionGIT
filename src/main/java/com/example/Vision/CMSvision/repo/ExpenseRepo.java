package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Integer> {
}
