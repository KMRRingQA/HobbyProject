package com.qa.service;

import com.qa.domain.Lift;
import com.qa.dto.LiftDTO;
import com.qa.exceptions.LiftNotFoundException;
import com.qa.repo.LiftsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiftService {

    private final LiftsRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public LiftService(LiftsRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private LiftDTO mapToDTO(Lift lift){
        return this.mapper.map(lift, LiftDTO.class);
    }


    public List<LiftDTO> readLifts(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public LiftDTO createLift(Lift lift){
        Lift tempLift = this.repo.save(lift);
        return this.mapToDTO(tempLift);
    }

    public LiftDTO findLiftById(Long id){
        return this.mapToDTO(this.repo.findById(id)
                .orElseThrow(LiftNotFoundException::new));
    }

    public LiftDTO updateLift(Long id, Lift lift){
        Lift update = this.repo.findById(id).orElseThrow(LiftNotFoundException::new);
        update.setTitle(lift.getTitle());
        update.setDescription(lift.getDescription());
        Lift tempLift = this.repo.save(lift);
        return this.mapToDTO(tempLift);
    }

    public boolean deleteLift(Long id){
        if(!this.repo.existsById(id)){
            throw new LiftNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }


}
