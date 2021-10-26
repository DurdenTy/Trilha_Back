package com.example.Clean.UseCases.Repositories;

import com.example.Clean.Entities.Entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
