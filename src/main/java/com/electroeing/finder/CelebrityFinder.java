package com.electroeing.finder;

import com.electroeing.finder.entities.Person;
import com.electroeing.finder.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            celebrity = Optional.of(team.get(0));
            for (int i = 1; i < team.size(); i++) {
                if (personKnowsTarget(celebrity.get(), team.get(i))) {
                    celebrity = Optional.of(team.get(i));
                }
            }

            for (Person p : team) {
                if (celebrity.get().getId().intValue() != p.getId().intValue()
                        && personKnowsTarget(celebrity.get(), p)) {
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
        return target.isCelebrity() || (!person.isCelebrity() && !target.isCelebrity());
    }
}
