package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.Income;
import com.example.Vision.CMSvision.entity.OpenClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income,Integer> {
/*    @Query(value ="select c from OpenClass c where c.classInfo.classId =:studentId and c.openDate>= :fromDate and c.openDate<= :toDate")
    List<OpenClass> getOpenClassByClassInfoClassId (@Param("studentId") int classId, @Param("fromDate") java.util.Date fromDate, @Param("toDate") Date toDate);
*/
    @Query(value = "select  i from Income i where i.receivedDate>= :fromDate and i.receivedDate<= :toDate")
    List<Income> getIncomeDetailBetween2Days(Date fromDate,Date toDate);
}
