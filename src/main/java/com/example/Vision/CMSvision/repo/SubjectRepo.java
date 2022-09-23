package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends JpaRepository<Subject,Integer> {
}
