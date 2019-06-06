package com.electroeing.finder;

import com.electroeing.finder.entities.Person;
import com.electroeing.finder.repository.TeamRepository;
import com.electroeing.finder.service.CelebrityFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CelebrityFinderTests {
    @InjectMocks
    private CelebrityFinder celebrityFinder;

    @Mock
    private TeamRepository teamRepositoryMock;

    private int teamSize = 20;
    private int random = new Random().ints(0, teamSize).limit(1).findFirst().orElse(2);

    @Test
    public void findCelebritySuccessTest() {
        Mockito.when(teamRepositoryMock.findAll()).thenReturn(generateTeamMock(teamSize, true));
        Optional<Person> result = celebrityFinder.findCelebrity();
        assertThat(result).isPresent();
        assertThat(result.orElse(null)).hasFieldOrPropertyWithValue("id", (long) random);
        assertThat(result.orElse(null)).hasFieldOrPropertyWithValue("celebrity", true);

    }

    @Test
    public void findCelebrityEmptyTeamTest() {
        Mockito.when(teamRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        Optional<Person> result = celebrityFinder.findCelebrity();
        assertThat(result).isEmpty();

    }

    @Test
    public void findCelebrityNoCelebrityTest() {
        Mockito.when(teamRepositoryMock.findAll()).thenReturn(generateTeamMock(teamSize, false));
        Optional<Person> result = celebrityFinder.findCelebrity();
        assertThat(result).isEmpty();

    }

    @Test
    public void findCelebrityTeamKnowOtherMembersTest() {
        // Create a list with 3 persons, 2 of them know each other
        List<Person> team = new ArrayList<>();
        Person p = new Person();
        p.setId(1L);
        p.setCelebrity(false);
        p.setKnown("2,3");
        team.add(p);

        p = new Person();
        p.setId(2L);
        p.setCelebrity(false);
        p.setKnown("1,3");
        team.add(p);

        p = new Person();
        p.setId(3L);
        p.setCelebrity(true);
        p.setKnown("");
        team.add(p);


        Mockito.when(teamRepositoryMock.findAll()).thenReturn(team);

        Optional<Person> result = celebrityFinder.findCelebrity();

        assertThat(result).isPresent();
        assertThat(result.orElse(null)).hasFieldOrPropertyWithValue("id", 3L);
        assertThat(result.orElse(null)).hasFieldOrPropertyWithValue("celebrity", true);

    }

    private List<Person> generateTeamMock(int teamSize, boolean withCelebrity) {
        List<Person> teamMock = new ArrayList<>();
        for (int i = 0; i < teamSize; i++) {
            Person p = new Person();
            p.setId((long) i);
            p.setCelebrity(i == random && withCelebrity);
            p.setKnown(p.isCelebrity() || !withCelebrity ? "" : String.valueOf(random));
            teamMock.add(p);
        }
        return teamMock;
    }

}
