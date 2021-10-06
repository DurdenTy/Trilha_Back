package com.example.financys.repository;

import com.example.financys.entry.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
