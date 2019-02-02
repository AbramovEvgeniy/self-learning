package com.application.service;

import com.application.model.Interest;
import com.application.model.Person;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SocialMediaService {

    void save(List<Person> persons);

    Map<ImmutablePair<String, String>, Set<Interest>> getPairs();
}
