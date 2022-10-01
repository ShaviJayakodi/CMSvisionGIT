package com.example.Vision.CMSvision.repo;

import com.example.Vision.CMSvision.entity.OpenClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenClassRepo extends JpaRepository<OpenClass, Integer> {

    @Query(value = "SELECT* FROM open_class WHERE status =?1",nativeQuery = true)
    List<OpenClass> findActiveClasses(int status);

}
