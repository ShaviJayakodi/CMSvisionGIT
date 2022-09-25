package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.OfficerDTO;
import com.example.Vision.CMSvision.entity.Officer;
import com.example.Vision.CMSvision.repo.OfficerRepo;
import com.example.Vision.CMSvision.enums.stakeHolderValues;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OfficerService {

    @Autowired
    private OfficerRepo officerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommonService commonService;


    public OfficerDTO saveOfficer(OfficerDTO officerDTO)
    {
        OfficerDTO officer = new OfficerDTO();
        if(officerDTO.getOfficerId()>0)
        {
            officer.setOfficerId(officerDTO.getOfficerId());
            officer.setFirstName(officerDTO.getFirstName());
            officer.setLastName(officerDTO.getLastName());
            officer.setAddress(officerDTO.getAddress());
            officer.setBirthDay(officerDTO.getBirthDay());
            officer.setAttendDate(officerDTO.getAttendDate());
            officer.setEmailAddress(officerDTO.getEmailAddress());
            officer.setDutyType(officerDTO.getDutyType());
            officer.setGender(officerDTO.getGender());
            officer.setMobNum1(officerDTO.getMobNum1());
            officer.setMobNum2(officerDTO.getMobNum2());
            officer.setRegNo(officerDTO.getRegNo());
        }
        else
        {
            int maxId= officerRepo.getMaxOfficerId();
            officer.setFirstName(officerDTO.getFirstName());
            officer.setLastName(officerDTO.getLastName());
            officer.setAddress(officerDTO.getAddress());
            officer.setBirthDay(officerDTO.getBirthDay());
            officer.setAttendDate(officerDTO.getAttendDate());
            officer.setEmailAddress(officerDTO.getEmailAddress());
            officer.setDutyType(officerDTO.getDutyType());
            officer.setGender(officerDTO.getGender());
            officer.setMobNum1(officerDTO.getMobNum1());
            officer.setMobNum2(officerDTO.getMobNum2());
            officer.setRegNo(Integer.parseInt(commonService.genarateRegNo(maxId,stakeHolderValues.OFFICER.code())));

        }
        officerRepo.save(modelMapper.map(officer, Officer.class));
        return officer;
    }

    public OfficerDTO getOfficerByRegNo(int officerRegNo)
    {
        Officer officer=officerRepo.getOfficerByRegNO(officerRegNo);
        return modelMapper.map(officer,OfficerDTO.class);

    }

    public OfficerDTO getOfficerById(int OfficerId)
    {
        return modelMapper.map(officerRepo.findById(OfficerId).get(),OfficerDTO.class);
    }

    public String deleteOfficerById(int officerId)
    {
        String status="";
        officerRepo.deleteById(officerId);
            status+="Teacher Deleted";
            return status;

    }

    public List<OfficerDTO> getAllOfficers() {
        List<Officer> officerList = officerRepo.findAll();
        return modelMapper.map(officerList,new TypeToken<List<OfficerDTO>>(){}.getType());
    }
}
