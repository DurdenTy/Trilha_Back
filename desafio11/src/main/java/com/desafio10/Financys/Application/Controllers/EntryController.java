package com.desafio10.Financys.Application.Controllers;

import com.desafio10.Financys.Domain.Entities.Entry;
import com.desafio10.Financys.Domain.Services.EntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Stream;

@RestController
public class EntryController {

    private EntryService entryService;

    public EntryController(EntryService entryService){
        this.entryService = entryService;
    }

    @PostMapping("/lancamentos")
    public Entry create(@RequestBody Entry entry){
        return entryService.create(entry);
    }

    @GetMapping("/lancamentos")
    public Stream<Entry> read(){
        return entryService.read();
    }

    @GetMapping("/lancamentos/{id}")
    public Entry readById(@PathVariable Long id){
        return entryService.findById(id);
    }

    @PutMapping("/lancamentos/{id}")
    public Entry update(@RequestBody Entry entry, @PathVariable Long id){
        return entryService.update(entry, id);
    }

    @DeleteMapping("/lancamentos/{id}")
    public void delete(@PathVariable Long id){
        entryService.delete(id);
    }

    @GetMapping("/lancamentos/filter")
    public List<Entry> getLancamentosDependentes(
        @RequestParam(value = "data", required = false) String data,
        @RequestParam(value = "amount", required = false) String amount,
        @RequestParam(value = "paid", required = false) Boolean paid
    ){

        return entryService.getLancamentosDependentes(data, amount, paid);

    }

}
