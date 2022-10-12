package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Integer> {
    /*    @Query(value ="select c from OpenClass c where c.classInfo.classId =:studentId and c.openDate>= :fromDate and c.openDate<= :toDate")
    List<OpenClass> getOpenClassByClassInfoClassId (@Param("studentId") int classId, @Param("fromDate") java.util.Date fromDate, @Param("toDate") Date toDate);
*/

    @Query(value = "select e from Expense e where e.date>= :fromDate and e.date<=:toDate")
    List<Expense> getExpenseBetween2Days(Date fromDate, Date toDate);
}
