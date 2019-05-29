package com.electroeing.finder.controller;

import com.electroeing.finder.CelebrityFinder;
import com.electroeing.finder.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class StartProcessController {
    @Autowired
    CelebrityFinder finder;

    @GetMapping("/find")
    public String findCelebrity() {
        Optional<Person> celebrity = finder.findCelebrity();
        if (celebrity.isPresent()) {
            return String.format("The celebrity is the person number %s", celebrity.get().getId());
        }
        return "The is no celebrity in the team";

    }
}
