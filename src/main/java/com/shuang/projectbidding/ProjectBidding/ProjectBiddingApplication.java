package com.shuang.projectbidding.ProjectBidding;

import com.shuang.projectbidding.ProjectBidding.batch.ProjectMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Timer;

@SpringBootApplication
public class ProjectBiddingApplication {

    private static final long CHECK_PERIOD = 1000;

    @Autowired
    private ProjectMonitor projectMonitor;

    public static void main(String[] args) {
        SpringApplication.run(ProjectBiddingApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            startMonitor(projectMonitor);
        };
    }

    private void startMonitor(ProjectMonitor projectMonitor) {
        Timer timer = new Timer();
        timer.schedule(projectMonitor, 0, CHECK_PERIOD);
    }


}
