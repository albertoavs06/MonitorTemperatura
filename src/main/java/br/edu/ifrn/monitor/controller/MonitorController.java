package br.edu.ifrn.monitor.controller;

import br.edu.ifrn.monitor.exception.ResourceNotFoundException;
import br.edu.ifrn.monitor.model.Monitor;

import br.edu.ifrn.monitor.repository.MonitorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MonitorController {

    @Autowired
    MonitorRepository monitorRepository;

 // @RequestMapping(value="/notes", method=RequestMethod.GET)
    @GetMapping("/temperatura")       
    public List<Monitor> getAllNotes() {
        return monitorRepository.findAll();
    }

    @PostMapping("/temperatura")
    public Monitor createTemp(@Valid @RequestBody Monitor note) {
        return monitorRepository.save(note);
    }

    @GetMapping("/temperatura/{id}")
    public Monitor getTempById(@PathVariable(value = "id") Long tempId) {
        return monitorRepository.findById(tempId).orElseThrow(() -> new ResourceNotFoundException("Temperatura", "id", tempId));
    }

    @PutMapping("/temperatura/{id}")
    public Monitor updateTemp(@PathVariable(value = "id") Long tempId, @Valid @RequestBody Monitor monitor) {

        Monitor temp = monitorRepository.findById(tempId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", tempId));

        temp.setTemperatura(monitor.getTemperatura());
        temp.setUmidade(monitor.getUmidade());

        Monitor updatedMonitor = monitorRepository.save(temp);
        return updatedMonitor;
    }

    @DeleteMapping("/temperatura/{id}")
    public ResponseEntity<?> deleteTemp(@PathVariable(value = "id") Long tempId) {
        Monitor temp = monitorRepository.findById(tempId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", tempId));

        monitorRepository.delete(temp);

        return ResponseEntity.ok().build();
    }
}
