package com.example.financys.controller;

import com.example.financys.entry.Entry;
import com.example.financys.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @PostMapping("/lancamentos")
    public Long create(@RequestBody Entry entry){

        System.out.println("Categoria identificada");
        for (Entry value:
             entryRepository.findAll()) {
            if(value.getId() == entry.getId()){
                System.out.println( "Id: " +entry.getId() + " já existente no banco de dados");
                return -1L;
            }
        }
        return entryRepository.save(entry).getId();
    }

    @GetMapping("/lancamentos")
    public List<Entry> read(){

        entryRepository.findAll().sort(Comparator.comparing(Entry::getDate));
        return entryRepository.findAll();

    }

    @GetMapping("/lancamentos/{id}")
    public Optional<Entry> findEntryById(Long id){
        entryRepository.findAll().sort(Comparator.comparing(Entry::getDate));
        return entryRepository.findById(id);
    }

    @PutMapping("/lancamentos/{id}")
    public ResponseEntity<Entry> update(@RequestBody Entry entry, @PathVariable("id") Long id) throws IllegalStateException{
        Entry entry2 = entryRepository.findById(id).orElseThrow(()
                -> new IllegalStateException(
                "Lançamento com id: " + id + " não encontrada"
        ));
        entry2.setId(entry.getId());
        entry2.setName(entry.getName());
        entry2.setDescription(entry.getDescription());
        entry2.setAmount(entry.getAmount());
        entry2.setType(entry.getType());
        entry2.setDate(entry.getDate());
        entry2.isPaid(entry.getPaid());
        entry2.setCategoryId(entry.getCategoryId());
        entryRepository.save(entry2);
        return ResponseEntity.ok().body(entry2);
    }

    @DeleteMapping("/lancamentos/{id}")
    public void delete(Long id){
        entryRepository.deleteById(id);
    }

}
