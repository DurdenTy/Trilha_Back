package com.desafio10.Financys.Domain.Services;


import com.desafio10.Financys.Application.Exceptions.NoContentException;
import com.desafio10.Financys.Application.Exceptions.ParametrosNulos;
import com.desafio10.Financys.Domain.Entities.Entry;
import com.desafio10.Financys.Infrastruct.Repositories.EntryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository){

        this.entryRepository = entryRepository;

    }

    public Entry create(Entry entry){

        return entryRepository.save(entry);

    }

    public Stream<Entry> read(){

        return entryRepository.findAll().stream().sorted(Comparator.comparing(Entry::getDate));

    }

    public Entry findById(Long id){

        Entry entry = entryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Lançamento com o id: " + id + " não encontrado"
        ));

        System.out.println("Lançamento encontrado!");
        return entry;

    }

    public Entry update(Entry entry, Long id){
        Entry entry2 = entryRepository.findById(id).orElseThrow(()
                -> new IllegalStateException(
                "Lançamento com id: " + id + " não encontrada"
        ));
        entry2.setId(entry.getId());
        entry2.setName(entry.getName());
        entry2.setDescription(entry.getDescription());
        entry2.setAmount(entry.getAmount());
        entry2.setTypes(entry.getTypes());
        entry2.setDate(entry.getDate());
        entry2.setPaid(entry.getPaid());
        entry2.setCategoryId(entry.getCategoryId());
        entryRepository.save(entry2);
        return entry2;
    }

    public void delete(Long id){
        entryRepository.deleteById(id);
    }

    public boolean validateEntryById(Entry entry){

        for (Entry value : entryRepository.findAll()) {
            if(value.getId() == entry.getId()){
                return true;
            }
        }
        return false;
    }

    public List<Entry> getLancamentosDependentes(String data, String amount, Boolean paid) throws NoContentException, NullPointerException{

        List<Entry> list = new ArrayList<>();

        try{
            if(data.equals(null) && amount.equals(null) && paid.equals(null)){

            }
        }catch(NullPointerException ex){
            throw new ParametrosNulos("Nenhum parâmetro foi passado");
        }

        if(data == null && amount == null){
            entryRepository.findAll().stream().forEach(value -> {
                if(value.getPaid() == paid){
                    list.add(value);
                }
            });

            return list;
        }

        if(data == null && paid == null){
            entryRepository.findAll().stream().forEach(value -> {
                if(value.getAmount() == amount){
                    list.add(value);
                }
            });

            return list;
        }

        if(amount == null && paid == null){
            entryRepository.findAll().stream().forEach(value -> {
                if(value.getDate() == data){
                    list.add(value);
                }
            });

            return list;
        }

        if(data == null){
            entryRepository.findAll().stream().forEach(value -> {
                if(value.getAmount() == (amount) || value.getPaid() == (paid)){
                    list.add(value);
                }
            });

            return list;
        }

        if(amount == null){
            entryRepository.findAll().stream().forEach(value -> {
                if(value.getDate() == (data) || value.getPaid() == (paid)){
                    list.add(value);
                }
            });

            return list;
        }

        if(paid == null){
            entryRepository.findAll().stream().forEach(value -> {
                if(value.getDate() == (data) || value.getDate() == (amount)){
                    list.add(value);
                }
            });

            return list;
        }

        entryRepository.findAll().stream().forEach(value -> {
            if(value.getDate() == (data) || value.getDate() == (amount) || value.getPaid() == (paid)){
                list.add(value);
            }
        });

        if(list.isEmpty()){
            throw new NoContentException("Não existem dados com os parâmetros passados");
        }

        return list;
    }

}
