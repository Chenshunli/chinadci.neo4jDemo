package com.chinadci.neo4j.utils;

import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AppUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
//        applicationContext = arg0;
        if(AppUtil.applicationContext == null) {
            AppUtil.applicationContext = arg0;
        }
    }

    public static Object getObject(String id) {
        Object object = null;
        object = applicationContext.getBean(id);
        return object;
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        Object ben = getApplicationContext().getBean(name);
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

//    public void run() throws Exception {
//        //获取springboot所有bean名称
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String bean : beanDefinitionNames) {
//            System.err.println(bean);
//        }
//    }

}
