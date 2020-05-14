package com.qa.service;

import com.qa.domain.Note;
import com.qa.domain.Manufacturer;
import com.qa.dto.ManufacturerDTO;
import com.qa.exceptions.ManufacturerNotFoundException;
import com.qa.repo.ManufacturerRepository;
import com.qa.repo.NotesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    private final NotesRepository notesRepository;

    private final ModelMapper mapper;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository, NotesRepository notesRepository, ModelMapper mapper) {
        this.manufacturerRepository = manufacturerRepository;
        this.notesRepository = notesRepository;
        this.mapper = mapper;
    }

    private ManufacturerDTO mapToDTO(Manufacturer manufacturer){
        return this.mapper.map(manufacturer, ManufacturerDTO.class);
    }

    public List<ManufacturerDTO> readManufacturers(){
        return this.manufacturerRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ManufacturerDTO createManufacturer(Manufacturer manufacturer){
        return this.mapToDTO(this.manufacturerRepository.save(manufacturer));
    }

    public ManufacturerDTO findManufacturerById(Long id){
        return this.mapToDTO(this.manufacturerRepository.findById(id).orElseThrow(ManufacturerNotFoundException::new));
    }

    public ManufacturerDTO updateManufacturer(Long id, Manufacturer manufacturer){
        Manufacturer update = this.manufacturerRepository.findById(id).orElseThrow(ManufacturerNotFoundException::new);
        update.setName(manufacturer.getName());
        Manufacturer tempManufacturer = this.manufacturerRepository.save(update);
        return this.mapToDTO(tempManufacturer);
    }

    public boolean deleteManufacturer(Long id){
        if(!this.manufacturerRepository.existsById(id)){
            throw new ManufacturerNotFoundException();
        }
        this.manufacturerRepository.deleteById(id);
        return this.manufacturerRepository.existsById(id);
    }

    public ManufacturerDTO addNoteToManufacturer(Long id, Note note){
        Manufacturer manufacturer = this.manufacturerRepository.findById(id).orElseThrow(ManufacturerNotFoundException::new);
        Note tmp = this.notesRepository.saveAndFlush(note);
        manufacturer.getNotes().add(tmp);
        return this.mapToDTO(this.manufacturerRepository.saveAndFlush(manufacturer));
    }

}
