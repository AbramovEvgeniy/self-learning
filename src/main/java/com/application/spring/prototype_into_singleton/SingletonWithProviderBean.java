package com.application.spring.prototype_into_singleton;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import javax.inject.Provider;


@Data
@Slf4j
@Component
@AllArgsConstructor
public class SingletonWithProviderBean {

    private Provider<PrototypeBean> prototypeBeanProvider;

    public void showMessage(){
        PrototypeBean bean = prototypeBeanProvider.get();
        log.info("Current date: " + bean.toString());
        //each time getPrototypeBean() call
        //will return new instance
    }

    public PrototypeBean getPrototypeBean() {
        return prototypeBeanProvider.get();
    }
}
