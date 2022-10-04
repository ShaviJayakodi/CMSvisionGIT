package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.ExpenseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseInfoRepo extends JpaRepository <ExpenseInfo,Integer> {
    @Query(value ="SELECT MAX(expense_info_id) FROM expense_info",nativeQuery = true)
    int getMaxExpenseId();
}
