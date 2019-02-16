package com.application.spring.prototype_into_singleton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Slf4j
public class SingletonBeanContextAware implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    public PrototypeBean getPrototypeBean() {
        PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
        log.info("Current date: " + prototypeBean.getCurrentDate());
        return prototypeBean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
