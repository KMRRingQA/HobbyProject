
package com.qa.service;

import com.qa.domain.Door;
import com.qa.domain.Lift;
import com.qa.dto.DoorDTO;
import com.qa.dto.LiftDTO;
import com.qa.exceptions.DoorNotFoundException;
import com.qa.repo.DoorsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoorService {

    private final DoorsRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public DoorService(DoorsRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private DoorDTO mapToDTO(Door door){
        return this.mapper.map(door, DoorDTO.class);
    }


    public List<DoorDTO> readDoors(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public DoorDTO createDoor(Door door){
        Door tempDoor = this.repo.save(door);
        return this.mapToDTO(tempDoor);
    }

    public DoorDTO findDoorById(Long id){
        return this.mapToDTO(this.repo.findById(id)
                .orElseThrow(DoorNotFoundException::new));
    }

    public DoorDTO updateDoor(Long id, Door door){
        Door update = this.repo.findById(id).orElseThrow(DoorNotFoundException::new);
        update.setId(id);
        update.setTitle(door.getTitle());
        update.setDescription(door.getDescription());
        update.setBwf(door.getBwf());
        update.setDimensions(door.getDimensions());
        update.setThermalResistance(door.getThermalResistance());
        update.setCost(door.getCost());
        repo.save(door);
        return this.mapToDTO(door);
    }

    public boolean deleteDoor(Long id){
        if(!this.repo.existsById(id)){
            throw new DoorNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}