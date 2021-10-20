package com.example.financys.service;

import com.example.financys.DTO.DtoChart;
import com.example.financys.entity.Entry;
import com.example.financys.repository.EntryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.management.relation.InvalidRelationIdException;
import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EntryService {

    private final EntryRepository entryRepository;

    private final ModelMapper modelMapper;

    private List<DtoChart> dtoChartList = new ArrayList<>();



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

    public Entry save(DtoChart dto_chart) throws InvalidRelationIdException {

        Entry entry = mapToEntry(dto_chart);

        if(validateEntryById(entry)){
            System.out.println( "Id: " + entry.getId() + " já existente no banco de dados");
            throw new InvalidRelationIdException("Id já existente no banco de dados");
        }

        System.out.println( "Novo lançamento com ID: " + entry.getId() + " criada!");

        return entryRepository.save(entry);

    }



    public List<Entry> findAll(){

        System.out.println("Lançamento identificado");
        entryRepository.findAll().sort(Comparator.comparing(Entry::getDate));
        return entryRepository.findAll();


    }

    public ResponseEntity<Entry> findById(Long id) throws EntityNotFoundException {

        Entry entry = entryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Lançamento com o id: " + id + " não encontrado"
        ));


        System.out.println("Lançamento identificado");
        return ResponseEntity.ok().body(entry);
    }

    public ResponseEntity<Entry> update(Long id, Entry entry) throws EntityNotFoundException{


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

    private DtoChart mapToDTO(Entry entry){

        DtoChart dtoChart = modelMapper.map(entry, DtoChart.class);
        return dtoChart;

    }

    public Entry mapToEntry(DtoChart dtoChart){
        Entry entry = modelMapper.map(dtoChart, Entry.class);
        return entry;
    }

    public Map<String, List<DtoChart>> retornarListaDTO(){

        dtoChartList.clear();

        entryRepository.findAll().stream().forEach(v ->
                dtoChartList.add(mapToDTO(v)));

        Map<String, List<DtoChart>> dtoChartGroupByType = dtoChartList.stream()
                .collect(Collectors.groupingBy(DtoChart::getType));

        return dtoChartGroupByType;

    }


    public Integer calculaMedia(Integer x, Integer y) throws ArithmeticException{

        try {
            return (x / y);
        }catch(ArithmeticException e){

            throw new ArithmeticException("Erro, divisor não pode ser zero");

        }

    }


}
