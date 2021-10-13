package com.example.financys.controller;

import com.example.financys.DTO.DTO_Chart;
import com.example.financys.entity.Entry;
import com.example.financys.service.EntryService;
import javassist.NotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.util.*;

@RestController
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService){

        this.entryService = entryService;

    }

    @PostMapping("/lancamentos")
    public Long create(@RequestBody DTO_Chart dto_chart){

        return entryService.save(dto_chart).getId();

    }

    //@GetMapping("/lancamentos")
    public List<Entry> read(){

        return entryService.findAll();

    }

    @GetMapping("/lancamentos/{id}")
    public ResponseEntity<Entry> findEntryById(@PathVariable() Long id) throws NotFoundException {

        return entryService.findById(id);

    }

    @PutMapping("/lancamentos/{id}")
    public ResponseEntity<Entry> update(@RequestBody Entry entry, @PathVariable() Long id) throws NotFoundException {

        return entryService.update(id, entry);

    }

    @DeleteMapping("/lancamentos/{id}")
    public void delete(@PathVariable() Long id) throws NotFoundException {
        entryService.delete(id);
    }

    @GetMapping("/lancamentos")
    public  Map<String, List<DTO_Chart>> readDTO(){
        return entryService.retornarListaDTO();
    }

}
