package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface HallRepo extends JpaRepository<Hall,Integer> {
}
