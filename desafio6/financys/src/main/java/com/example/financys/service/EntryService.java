package com.example.financys.service;

import com.example.financys.DTO.DTO_Chart;
import com.example.financys.entity.Entry;
import com.example.financys.repository.EntryRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EntryService {

    private final EntryRepository entryRepository;

    private final ModelMapper modelMapper;

    private List<DTO_Chart> dtoChartList = new ArrayList<>();



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

    public Entry save(DTO_Chart dto_chart){

        Entry entry = mapToEntry(dto_chart);

        if(validateEntryById(entry)){
            System.out.println( "Id: " + entry.getId() + " já existente no banco de dados");
            return null;
        }

        System.out.println( "Novo lançamento com ID: " + entry.getId() + " criada!");
        return entryRepository.save(entry);
    }



    public List<Entry> findAll(){

        System.out.println("Lançamento identificado");
        entryRepository.findAll().stream().sorted(Comparator.comparing(Entry::getDate));
        return entryRepository.findAll();

    }

    public ResponseEntity<Entry> findById(Long id) throws NotFoundException {

        Entry entry = entryRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Lançamento com o id: " + id + " não encontrado"
        ));
        System.out.println("Lançamento identificado");
        return ResponseEntity.ok().body(entry);
    }

    public ResponseEntity<Entry> update(Long id, Entry entry) throws NotFoundException{


        Entry entry2 = entryRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Lançamento com o id: " + id + " não encontrado"
        ));
        entry2 = entry;
        entry2.setId(entry.getId());
        entryRepository.save(entry2);
        System.out.println("Lançamento identificado");

        return ResponseEntity.ok().body(entry2);
    }

    public void delete(Long id) throws NotFoundException{

        Entry entry = entryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Lançamento com o id: " + id + " não encontrado" ));

        entryRepository.deleteById(id);
    }

    private DTO_Chart mapToDTO(Entry entry){

        DTO_Chart dto_chart = modelMapper.map(entry, DTO_Chart.class);
        return dto_chart;

    }

    public Entry mapToEntry(DTO_Chart dto_chart){
        Entry entry = modelMapper.map(dto_chart, Entry.class);
        return entry;
    }

    public DTO_Chart mapToDTO_Chart(Entry entry){
        DTO_Chart dto_chart = modelMapper.map(entry, DTO_Chart.class);
        return dto_chart;
    }

    public Map<String, List<DTO_Chart>> retornarListaDTO(){

        dtoChartList.clear();

        for(Entry entry : entryRepository.findAll()){
            dtoChartList.add(mapToDTO_Chart(entry));
        }

        Map<String, List<DTO_Chart>> dtoChartGroupByType= new HashMap<>();

        for(DTO_Chart item : dtoChartList){
            if(!dtoChartGroupByType.containsKey(item.getType())){
                dtoChartGroupByType.put(item.getType(), new ArrayList<>());
            }
            dtoChartGroupByType.get(item.getType()).add(item);
        }

        return dtoChartGroupByType;

    }


}
