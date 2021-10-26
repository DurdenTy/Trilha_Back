package com.example.DDD.Application;

import com.example.DDD.Domain.ValueObject.ObjectChart;
import com.example.DDD.Domain.Domain.Entry;
import com.example.DDD.Services.EntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class EntryApplication {

    private final EntryService entryService;

    public EntryApplication(EntryService entryService){

        this.entryService = entryService;

    }

    @PostMapping("/lancamentos")
    public Long create(@RequestBody @Valid ObjectChart objectChart) {

        return entryService.save(objectChart).getId();

    }

    public List<Entry> read(){

        return entryService.findAll();

    }

    @GetMapping("/lancamentos/{id}")
    public ResponseEntity<Entry> findEntryById(@PathVariable Long id) {

        return entryService.findById(id);

    }

    @PutMapping("/lancamentos/{id}")
    public ResponseEntity<Entry> update(@RequestBody @Valid Entry entry, @PathVariable Long id) {

        return entryService.update(id, entry);

    }

    @DeleteMapping("/lancamentos/{id}")
    public void delete(@PathVariable Long id) {
        entryService.delete(id);
    }

    @GetMapping("/lancamentos")
    public Map<String, List<ObjectChart>> readDTO(){
        return entryService.retornarListaDTO();
    }

    @GetMapping("/calcula/{x}/{y}")
    public Integer calculaMedia(@PathVariable(value = "x") Integer x,
                                @PathVariable(value = "y") Integer y){
        return entryService.calculaMedia(x, y);
    }

}
