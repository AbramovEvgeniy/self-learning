package com.application.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LoginQueueUsingSemaphoreTest.class},
        loader = SpringBootContextLoader.class)
public class LoginQueueUsingSemaphoreTest {

    @Test
    public void givenLoginQueue_whenReachLimit_thenBlocked() {
        int slots = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots);
        IntStream.range(0, slots)
                .forEach(user -> executorService.execute(loginQueue::tryLogin));
        executorService.shutdown();

        assertEquals(0, loginQueue.availableSlots());
        assertFalse(loginQueue.tryLogin());
    }

//    @Test
//    public void givenLoginQueue_whenLogout_thenSlotsAvailable() {
//        int slots = 10;
//        ExecutorService executorService = Executors.newFixedThreadPool(slots);
//        LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots);
//        IntStream.range(0, slots)
//                .forEach(user -> executorService.execute(loginQueue::tryLogin));
//        executorService.shutdown();
//        assertEquals(0, loginQueue.availableSlots());
//        loginQueue.logout();
//
//        assertTrue(loginQueue.availableSlots() > 0);
//        assertTrue(loginQueue.tryLogin());
//    }
}