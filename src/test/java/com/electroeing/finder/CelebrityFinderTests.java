package com.electroeing.finder;

import com.electroeing.finder.entities.Person;
import com.electroeing.finder.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    private List<Person> generateTeamMock(int teamSize, boolean withCelebrity) {
        List<Person> teamMock = new ArrayList<>();
        for (int i = 0; i < teamSize; i++) {
            Person p = new Person();
            p.setId((long) i);
            p.setCelebrity(i == random && withCelebrity);
            teamMock.add(p);
        }
        return teamMock;
    }

}
