package com.electroeing.finder.service;

import com.electroeing.finder.entities.Person;
import com.electroeing.finder.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CelebrityFinder {
    private static final Logger logger = LoggerFactory.getLogger(CelebrityFinder.class);

    @Autowired
    private TeamRepository teamRepository;

    public Optional<Person> findCelebrity() {
        List<Person> team = teamRepository.findAll();
        logger.info("Team size loaded: {}", team.size());
        logger.debug("Team: {}", team);

        Optional<Person> celebrity = Optional.empty();
        if (!team.isEmpty()){
            Deque<Person> teamStack = new LinkedList<>(team);
            while (teamStack.size() > 1) {
                Person a = teamStack.pop();
                Person b = teamStack.pop();

                if (personKnowsTarget(a, b)) {
                    teamStack.push(b);
                } else {
                    teamStack.push(a);
                }
            }
            celebrity = Optional.of(teamStack.pop());

            for (Person p : team) {
                if (!celebrity.get().getId().equals(p.getId())
                        && (personKnowsTarget(celebrity.get(), p) || !personKnowsTarget(p, celebrity.get()))) {
                    return Optional.empty();
                }
            }
        }

        logger.info("Celebrity: {}", celebrity);

        return celebrity;
    }

    /**
     * Returns true if target is a celebrity or both are not celebrities
     *
     * @param person: person that is been asked to know another
     * @param target: person which is been talked about
     * @return : true only if person knows target
     */
    private boolean personKnowsTarget(Person person, Person target) {
        List<String> knownPersons = Arrays.asList(person.getKnown().split("\\s*,\\s*"));
        return knownPersons.contains(target.getId().toString());
    }
}
