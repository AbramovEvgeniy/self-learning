package com.application.service;

import com.application.model.Interest;
import com.application.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SocialMediaServiceImpl implements SocialMediaService {

    // private Map<String, Person> personsMap = new ConcurrentHashMap();
    private List<Person> personsList = new Vector<>();

    @Override
    public void save(List<Person> persons) {
        persons.forEach(person -> personsList.add(person));
    }

    @Override
    public Map<ImmutablePair<String, String>, Set<Interest>> getPairs() {
        HashMap<ImmutablePair<String, String>, Set<Interest>> completeResult = new HashMap<>();
        HashMap<ImmutablePair<String, String>, Set<Interest>> buildPairsResult = buildPairs();

        completeResult = recursion(completeResult, buildPairsResult);

        return completeResult;
    }

    private HashMap<ImmutablePair<String, String>, Set<Interest>> recursion(HashMap<ImmutablePair<String, String>, Set<Interest>> completeResult, HashMap<ImmutablePair<String, String>, Set<Interest>> buildPairsResult) {
        HashMap<ImmutablePair<String, String>, Set<Interest>> intermediateResult = new HashMap<>();
        Optional<Map.Entry<ImmutablePair<String, String>, Set<Interest>>> value = buildPairsResult.entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().size()));
        int max = value.get().getValue().size();
//        while (max != 1) {
            int finalMax = max;
            Set<ImmutablePair<String, String>> maxKeyList = buildPairsResult.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().size() == finalMax)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());
            //Итерация + удаление с такими именами
//            for (ImmutablePair<String, String> pair : maxKeyList) {
        ImmutablePair<String, String> pair = maxKeyList.iterator().next();
                HashMap<ImmutablePair<String, String>, Set<Interest>> clone = SerializationUtils.clone(buildPairsResult);
                clone.keySet()
                        .removeIf(cloneValue -> {
                            return cloneValue.getLeft().equals(pair.getLeft()) ||
                                    cloneValue.getLeft().equals(pair.getRight()) ||
                                    cloneValue.getRight().equals(pair.getLeft()) ||
                                    cloneValue.getRight().equals(pair.getRight());
                        });
                completeResult.put(pair, buildPairsResult.get(pair));
//                result.putAll(clone);
                while (clone.size() > 1) {
                  return recursion(completeResult ,clone);
                }
                completeResult.putAll(clone);
//                result.putAll(clone);
//                if (completeResult.size() < result.size()) {
//                    completeResult = result;
//                }
//            }
//            max = max - 1;
//        }
        return completeResult;
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
                }
            }
        }
        return map;
    }

}
