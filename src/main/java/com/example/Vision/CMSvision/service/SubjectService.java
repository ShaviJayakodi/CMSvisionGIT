package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.GradeDTO;
import com.example.Vision.CMSvision.dto.SubjectDTO;
import com.example.Vision.CMSvision.entity.Subject;
import com.example.Vision.CMSvision.repo.SubjectRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;
    @Autowired
    private ModelMapper modelMapper;

    public SubjectDTO saveSubject(SubjectDTO subjectDTO)
    {
        subjectRepo.save(modelMapper.map(subjectDTO, Subject.class));
        return subjectDTO;
    }


    public List<SubjectDTO> getAllSubjects() {
        List<Subject> subjectList = subjectRepo.findAll();
        return modelMapper.map(subjectList,new TypeToken<List<SubjectDTO>>(){}.getType());
    }

    public SubjectDTO getUniqueById(int subjectId) {

       return modelMapper.map(subjectRepo.findById(subjectId).get(),SubjectDTO.class);
    }
}
