package com.example.DDD.Infrastructure.Repositories;

import com.example.DDD.Domain.Domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
