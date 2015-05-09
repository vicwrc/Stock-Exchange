package com.luxoft.stockexchange.emulator;

import com.luxoft.stockexchange.emulator.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import quickfix.ConfigError;

/**
 * Created by victorvorontsov on 09.05.15.
 */
public class FixEmulator {

    public Application server;
    public Application client;

    public void start() throws ConfigError {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        server = (Application) context.getBean("server");
        client = (Application) context.getBean("client");

        server.start();
        client.start();
    }

    public void stop() {
        server.stop();
        client.stop();
    }

}
