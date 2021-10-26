package com.example.DDD.Services;

import com.example.DDD.Domain.ValueObject.ObjectChart;
import com.example.DDD.Domain.Domain.Entry;
import com.example.DDD.Services.exception.ExceptionCalculaMedia;
import com.example.DDD.Infrastructure.Repositories.EntryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EntryService {

    private final EntryRepository entryRepository;

    private final ModelMapper modelMapper;

    private List<ObjectChart> objectChartList = new ArrayList<>();



    public EntryService(EntryRepository entryRepository, ModelMapper modelMapper){
        this.entryRepository = entryRepository;
        this.modelMapper = modelMapper;
    }

    public boolean validateEntryById(Entry entry){

        for (Entry value : entryRepository.findAll()) {
            if(value.getId() == entry.getId()){
                return true;
            }
        }
        return false;
    }

    public Entry save(ObjectChart dto_chart) {

        Entry entry = mapToEntry(dto_chart);

        if(validateEntryById(entry)){
            System.out.println( "Id: " + entry.getId() + " já existente no banco de dados");
        }

        System.out.println( "Novo lançamento com ID: " + entry.getId() + " criada!");

        return entryRepository.save(entry);

    }



    public List<Entry> findAll(){

        System.out.println("Lançamento identificado");
        entryRepository.findAll().sort(Comparator.comparing(Entry::getDate));
        return entryRepository.findAll();


    }

    public ResponseEntity<Entry> findById(Long id) {

        Entry entry = entryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Lançamento com o id: " + id + " não encontrado"
        ));


        System.out.println("Lançamento identificado");
        return ResponseEntity.ok().body(entry);
    }

    public ResponseEntity<Entry> update(Long id, Entry entry) {


        Entry entry2 = entryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Lançamento com o id: " + id + " não encontrado"
        ));

        entry2 = entry;
        entry2.setId(entry.getId());
        entryRepository.save(entry2);
        System.out.println("Lançamento identificado");

        return ResponseEntity.ok().body(entry2);
    }

    public void delete(Long id) throws EntityNotFoundException{

        Entry entry = entryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Lançamento com o id: " + id + " não encontrado" ));

        entryRepository.deleteById(id);
    }

    private ObjectChart mapToDTO(Entry entry){

        ObjectChart objectChart = modelMapper.map(entry, ObjectChart.class);
        return objectChart;

    }

    public Entry mapToEntry(ObjectChart objectChart){
        Entry entry = modelMapper.map(objectChart, Entry.class);
        return entry;
    }

    public Map<String, List<ObjectChart>> retornarListaDTO(){

        objectChartList.clear();

        entryRepository.findAll().stream().forEach(v ->
                objectChartList.add(mapToDTO(v)));

        Map<String, List<ObjectChart>> dtoChartGroupByType = objectChartList.stream()
                .collect(Collectors.groupingBy(ObjectChart::getType));

        return dtoChartGroupByType;

    }


    public Integer calculaMedia(Integer x, Integer y) {

        try {
            return (x / y);
        }catch(ArithmeticException e){

            throw new ExceptionCalculaMedia("Erro, divisor não pode ser zero");

        }

    }


}
