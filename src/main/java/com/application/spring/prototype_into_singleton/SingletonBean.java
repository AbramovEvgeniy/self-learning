package com.application.spring.prototype_into_singleton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SingletonBean {
    @Autowired
    private PrototypeBean prototypeBean;

    public SingletonBean() {
        log.info("Singleton instance created");
    }

    public PrototypeBean getPrototypeBean() {
        log.info("Current date: " + prototypeBean.getCurrentDate());
        return prototypeBean;
    }
}
