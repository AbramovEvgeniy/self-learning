package com.application.service;

import com.application.model.Person;
import javafx.util.Pair;

import java.util.List;

public interface SocialMediaService {

    void save(List<Person> persons);

    List<Pair<Person, Person>> getPairs();
}
