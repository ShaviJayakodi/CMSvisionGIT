package com.example.Vision.CMSvision.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ClassFeeWithdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int withdrawId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    private double fullAmount;
    private  double commission;
    private double teacherAmount;
    @Temporal(TemporalType.DATE)
    private Date withdrawDate;


}
