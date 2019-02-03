package com.application.service;

import com.application.model.Interest;
import com.application.model.Person;
import com.application.utils.Node;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SocialMediaServiceImpl implements SocialMediaService {

    private List<Person> personsList = new Vector<>();

    @Override
    public void save(List<Person> persons) {
        persons.forEach(person -> personsList.add(person));
    }

    @Override
    public Map<ImmutablePair<String, String>, Set<Interest>> getPairs() {
        Node<ImmutablePair<String, String>> finalNode = new Node<>(null);
        HashMap<ImmutablePair<String, String>, Set<Interest>> buildPairsResult = buildPairs();

        int maxInterestsCount = maxInterestsCount(buildPairsResult);

        while (maxInterestsCount != 0) {
            populateNode(buildPairsResult, maxInterestsCount, finalNode);
            maxInterestsCount = maxInterestsCount - 1;
        }
        List<ImmutablePair<String, String>> smsList = new ArrayList<>();
        finalNode.collectResult(smsList);

        return smsList.stream().collect(Collectors.toMap(Function.identity(), buildPairsResult::get));
    }

    private void populateNode(HashMap<ImmutablePair<String, String>, Set<Interest>> availablePairs,
                              int maxInterestsCount,
                              Node<ImmutablePair<String, String>> node) {

        Set<ImmutablePair<String, String>> maxKeyList = availablePairs.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() == maxInterestsCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        for (ImmutablePair<String, String> pair : maxKeyList) {
            HashMap<ImmutablePair<String, String>, Set<Interest>> clone = SerializationUtils.clone(availablePairs);
            HashMap<ImmutablePair<String, String>, Set<Interest>> iterationResult = new HashMap<>();

            clone.keySet()
                    .removeIf(cloneValue -> {
                        return cloneValue.getLeft().equals(pair.getLeft()) ||
                                cloneValue.getLeft().equals(pair.getRight()) ||
                                cloneValue.getRight().equals(pair.getLeft()) ||
                                cloneValue.getRight().equals(pair.getRight());
                    });

            int weight = calcWeight(availablePairs, pair);
            Node<ImmutablePair<String, String>> childNode = new Node<>(pair, weight);

            node.getChildren().add(childNode);
            if (clone.size() >= 1) {
                populateNode(clone, maxInterestsCount(clone), childNode);
            }
        }
    }

    private HashMap<ImmutablePair<String, String>, Set<Interest>> buildPairs() {
        HashMap<ImmutablePair<String, String>, Set<Interest>> map = new HashMap<>();
        for (int i = 0; i <= personsList.size() - 1; i++) {
            for (int j = i + 1; j <= personsList.size() - 1; j++) {
                ImmutablePair<String, String> key = ImmutablePair.of(personsList.get(i).getName(), personsList.get(j).getName());
                Set<Interest> interests = personsList.get(i).getInterests()
                        .stream()
                        .filter(personsList.get(j).getInterests()::contains)
                        .collect(Collectors.toSet());
                if (!CollectionUtils.isEmpty(interests)) {
                    map.put(key, interests);
                    log.debug("Pair is: " + key + "Interests are: " + StringUtils.join(interests));
                }
            }
        }
        return map;
    }

    private int maxInterestsCount(HashMap<ImmutablePair<String, String>, Set<Interest>> availablePairs) {
        return availablePairs.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .max(Comparator.comparing(Set::size))
                .map(Set::size)
                .get();
    }

    private int calcWeight(HashMap<ImmutablePair<String, String>, Set<Interest>> availablePairs, ImmutablePair<String, String> pair) {
        return availablePairs.get(pair).size();
    }
}
