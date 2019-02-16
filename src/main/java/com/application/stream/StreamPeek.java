package com.application.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamPeek {

    public static void printNames() {
        Stream<String> nameStream = Stream.of("Alice", "Bob", "Chuck");
        nameStream.peek(log::info).collect(Collectors.toList());
    }

    public static void peek() {
        Stream<String> nameStream = Stream.of("Alice", "Bob", "Chuck");
        nameStream.filter(name -> name.length() > 1)
                .sorted(Comparator.comparingInt(String::length))
                .map(String::length)
                .peek(value -> log.info(value.toString()))
                .toArray();
    }

    public static void flatMap() {
        Department department = new Department(Arrays.asList(
                new StreamPeek.User("Bob", 1,2,3),
                new StreamPeek.User("Linda", 3,4,5),
                new StreamPeek.User("Bob", 3,5,9)));

        department.userList.stream()
                .flatMap(value -> Stream.of(value.params))
                .distinct()
                .peek(value -> log.info(value.toString()))
                .toArray();
        log.info("---------------------------");
        department.userList.stream()
                .flatMap(value -> Stream.of(value.params))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(value -> value.getValue() == 1)
                                    .peek(intValue -> log.info(intValue.getKey().toString()))
                                    .toArray();
        log.info("---------------------------");

    }

    private static class Department{
        private List<User> userList;

        public Department(List<User> userList) {
            this.userList = userList;
        }
    }

    private static class User {
        private String userName;
        private Integer [] params;

        public User(String userName, Integer ... params) {
            this.userName = userName;
            this.params = params;
        }
    }
}
