package com.application.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
class LoginQueueUsingSemaphore {

    private Semaphore semaphore;

    public LoginQueueUsingSemaphore(int slotLimit) {
        semaphore = new Semaphore(slotLimit);
    }

    synchronized boolean tryLogin() {
        log.debug("Thread name : " + Thread.currentThread().getName());
        boolean result = semaphore.tryAcquire();
        try {

            this.wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    void logout() {
        semaphore.release();
    }

    int availableSlots() {
        return semaphore.availablePermits();
    }

}
