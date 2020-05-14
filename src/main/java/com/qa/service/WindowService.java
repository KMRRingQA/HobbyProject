
package com.qa.service;

import com.qa.domain.Window;
import com.qa.dto.WindowDTO;
import com.qa.exceptions.WindowNotFoundException;
import com.qa.repo.WindowsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WindowService {

    private final WindowsRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public WindowService(WindowsRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private WindowDTO mapToDTO(Window window){
        return this.mapper.map(window, WindowDTO.class);
    }


    public List<WindowDTO> readWindows(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public WindowDTO createWindow(Window window){
        Window tempWindow = this.repo.save(window);
        return this.mapToDTO(tempWindow);
    }

    public WindowDTO findWindowById(Long id){
        return this.mapToDTO(this.repo.findById(id)
                .orElseThrow(WindowNotFoundException::new));
    }

    public WindowDTO updateWindow(Long id, Window window){
        Window update = this.repo.findById(id).orElseThrow(WindowNotFoundException::new);
        update.setTitle(window.getTitle());
        update.setDescription(window.getDescription());
        Window tempWindow = this.repo.save(window);
        return this.mapToDTO(tempWindow);
    }

    public boolean deleteWindow(Long id){
        if(!this.repo.existsById(id)){
            throw new WindowNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}