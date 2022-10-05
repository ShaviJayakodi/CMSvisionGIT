package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <User,Integer> {

    @Query(value = "SELECT * FROM `user`WHERE user_name=?1 AND pass_word =?2",nativeQuery = true)
    User checkUser(String userName, String passWord);

}
