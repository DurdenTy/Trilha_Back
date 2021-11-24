package com.desafio10.Financys.Infrastruct.Repositories;

import com.desafio10.Financys.Domain.Entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
