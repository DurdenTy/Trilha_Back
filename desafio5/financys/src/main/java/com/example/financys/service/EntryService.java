package com.example.financys.service;

import com.example.financys.entity.Entry;
import com.example.financys.repository.EntryRepository;

public class EntryService {

    public boolean validateEntryById(EntryRepository entryRepository, Entry entry){

        for (Entry value : entryRepository.findAll()) {
            if(value.getId() == entry.getId()){
                return true;
            }
        }
        return false;
    }

}
