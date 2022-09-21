package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <User,Integer> {

}
