package com.electroeing.finder.repository;

import com.electroeing.finder.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Person, Long> {

}
