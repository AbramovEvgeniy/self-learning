package com.application.spring.prototype_into_singleton;

import com.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.util.Assert.isTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class},
        loader = SpringBootContextLoader.class)
public class SingletonBeanContextAwareTest {
    @Test
    public void testThanInstantOfPrototypeTheSame() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(Application.class);

        SingletonBean firstSingleton = context.getBean(SingletonBean.class);
        PrototypeBean firstPrototype = firstSingleton.getPrototypeBean();

        // get singleton bean instance one more time
        SingletonBean secondSingleton = context.getBean(SingletonBean.class);
        PrototypeBean secondPrototype = secondSingleton.getPrototypeBean();

        isTrue(firstPrototype.equals(secondPrototype), "The same instance should be returned");
    }

    @Test
    public void testThanInstantOfPrototypeNotSame() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(Application.class);

        SingletonBeanContextAware firstSingleton = context.getBean(SingletonBeanContextAware.class);
        PrototypeBean firstPrototype = firstSingleton.getPrototypeBean();

        // get singleton bean instance one more time
        SingletonBeanContextAware secondSingleton = context.getBean(SingletonBeanContextAware.class);
        PrototypeBean secondPrototype = secondSingleton.getPrototypeBean();

        isTrue(!firstPrototype.equals(secondPrototype), "The same instance should be returned");
    }

    @Test
    public void testThanInstantOfPrototypeNotSameWithProvider() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(Application.class);

        SingletonWithProviderBean firstSingleton = context.getBean(SingletonWithProviderBean.class);
        PrototypeBean firstPrototype = firstSingleton.getPrototypeBean();

        // get singleton bean instance one more time
        SingletonWithProviderBean secondSingleton = context.getBean(SingletonWithProviderBean.class);
        PrototypeBean secondPrototype = secondSingleton.getPrototypeBean();

        isTrue(!firstPrototype.equals(secondPrototype), "The same instance should be returned");
    }

    @Test
    public void testThanInstantOfPrototypeNotSameWithObjectFactory() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(Application.class);

        SingletonObjectFactoryBean firstSingleton = context.getBean(SingletonObjectFactoryBean.class);
        PrototypeBean firstPrototype = firstSingleton.getPrototypeInstance();

        // get singleton bean instance one more time
        SingletonObjectFactoryBean secondSingleton = context.getBean(SingletonObjectFactoryBean.class);
        PrototypeBean secondPrototype = secondSingleton.getPrototypeInstance();

        isTrue(!firstPrototype.equals(secondPrototype), "The same instance should be returned");
    }
}