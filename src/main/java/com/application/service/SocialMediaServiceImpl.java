package com.application.service;

import com.application.model.Person;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class SocialMediaServiceImpl implements SocialMediaService {

    private Map<String, Person> personsMap = new ConcurrentHashMap();

    @Override
    public void save(List<Person> persons) {
        persons.forEach(person -> personsMap.put(person.getName(), person));
    }

    @Override
    public List<Pair<Person, Person>> getPairs() {
        Set<Map.Entry<String, Person>> persons = personsMap.entrySet();
        return persons.stream();
    }

    private long maxPairsCounter() {
        personsMap.entrySet().stream()
                .map(value -> value.getValue().getInterests())
                .reduce(
                0,
                (first, second) -> Collections.disjoint(first, second) ? first : second);
    }
}
