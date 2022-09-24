package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.GradeDTO;
import com.example.Vision.CMSvision.dto.StudentDTO;
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

    public SubjectDTO saveSubject(SubjectDTO subjectDTO) {
        SubjectDTO subject = new SubjectDTO();
        if (subjectDTO.getSubjectId() > 0) {
            subject.setSubjectId(subjectDTO.getSubjectId());
            subject.setSubjectCode(subjectDTO.getSubjectCode());
            subject.setSubjectName(subjectDTO.getSubjectName());
        } else {

            subject.setSubjectCode(subjectDTO.getSubjectCode());
            subject.setSubjectName(subjectDTO.getSubjectName());
        }
        subjectRepo.save(modelMapper.map(subject, Subject.class));
        return subject;
    }


    public List<SubjectDTO> getAllSubjects() {
        List<Subject> subjectList = subjectRepo.findAll();
        return modelMapper.map(subjectList, new TypeToken<List<SubjectDTO>>() {
        }.getType());
    }

    public SubjectDTO getUniqueById(int subjectId) {

        return modelMapper.map(subjectRepo.findById(subjectId).get(), SubjectDTO.class);
    }

    public String deleteSubject(int subjectId) {

        String status = "";
        if (subjectRepo.existsById(subjectId)) {
            subjectRepo.deleteById(subjectId);
            status += "Successful Deleted";
        } else {
            status += "Subject Not Found!";
        }

        return status;
    }


}