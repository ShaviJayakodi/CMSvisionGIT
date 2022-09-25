package com.example.Vision.CMSvision.service;

import com.example.Vision.CMSvision.dto.HallDTO;
import com.example.Vision.CMSvision.entity.Hall;
import com.example.Vision.CMSvision.repo.HallRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HallService {

    @Autowired
    private HallRepo hallRepo;

    @Autowired
    private ModelMapper modelMapper;
    public HallDTO saveHall(HallDTO hallDTO) {
       HallDTO hall = new HallDTO();
       if(hallDTO.getHallId()>0)
       {
           hall.setHallId(hallDTO.getHallId());
           hall.setHallCode(hallDTO.getHallCode());
           hall.setHallName(hallDTO.getHallName());
           hall.setFloor(hall.getFloor());
       }
       else
       {
           hall.setHallCode(hallDTO.getHallCode());
           hall.setHallName(hallDTO.getHallName());
           hall.setFloor(hall.getFloor());
       }

        hallRepo.save(modelMapper.map(hallDTO,Hall.class));
       return hallDTO;
    }

    public List<HallDTO> getAllHalls()
    {
        List<Hall> hallList =hallRepo.findAll();
        return modelMapper.map(hallList,new TypeToken<List<Hall>>(){}.getType());
    }

    public HallDTO getByHallId(int hallId)
    {
        return modelMapper.map(hallRepo.findById(hallId).get(),HallDTO.class);
    }

    public String deleteById(int hallId)
    {
        String status="";
        if(hallRepo.existsById(hallId))
        {
            hallRepo.deleteById(hallId);
            status+="Successfully Deleted";
        }
        else
        {
            status+= "Grade Id Not Exist";
        }
        return status;
    }

}
