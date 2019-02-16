package com.application.onedome;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OneDomeService {

    private Predicate<LoginEvent> predicate =
            loginEvent -> Instant.now().getEpochSecond() - loginEvent.getLoginDate() < 3 * ChronoUnit.DAYS.getDuration().getSeconds();

    private Predicate<LoginEvent> simplePredicate =
            loginEvent -> 7 - loginEvent.getLoginDate() <= 3;

    public List<String> getLoyalEmails(List<LoginEvent> events) {
        return events.stream()
                .filter(simplePredicate)
                .collect(Collectors.groupingBy(LoginEvent::getLoginEmail, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >= 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
