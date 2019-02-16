package com.application.onedome;

import com.application.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {Application.class},
//        loader = SpringBootContextLoader.class)
public class OneDomeServiceTest {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
    private List<LoginEvent> loginEvents;

    DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                    .withLocale(Locale.getDefault())
                    .withZone(ZoneId.systemDefault());

    @Test
    public void getLoyalEmails() {
        OneDomeService oneDomeService = new OneDomeService();
        List<String> emails = oneDomeService.getLoyalEmails(loginEvents);
        System.out.println(LocalDateTime.now().getYear());

    }

    @Before
    public void prepareLoginList() {
        loginEvents = new ArrayList<>();
        loginEvents.add(new LoginEvent("user1", 1));
        loginEvents.add(new LoginEvent("user1", 2));
        loginEvents.add(new LoginEvent("user1", 3));
        
        
        
        loginEvents.add(new LoginEvent("user2", 2));
        loginEvents.add(new LoginEvent("user2", 3));
        loginEvents.add(new LoginEvent("user2", 4));

        loginEvents.add(new LoginEvent("user3", 4));
        loginEvents.add(new LoginEvent("user3", 5));
        loginEvents.add(new LoginEvent("user3", 6));
        
        
        loginEvents.add(new LoginEvent("user4", 2));
        loginEvents.add(new LoginEvent("user4", 4));
        loginEvents.add(new LoginEvent("user4", 5));

    }
}