package com.github.cleanrpg.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.ExecutorService;

@Configuration
public class ClientsConfig {

    @Bean
    public ThreadPoolExecutorFactoryBean clientsThreadPoolsFactory() {
        ThreadPoolExecutorFactoryBean factory = new ThreadPoolExecutorFactoryBean();
        factory.setCorePoolSize(10);
        factory.setThreadNamePrefix("player-");
        return factory;
    }

    @Bean
    @Qualifier("clients")
    public ExecutorService clients() {
        try {
            return clientsThreadPoolsFactory().getObject();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
