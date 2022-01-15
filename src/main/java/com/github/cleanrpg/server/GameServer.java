package com.github.cleanrpg.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@SpringBootApplication
@Slf4j
public class GameServer implements CommandLineRunner {

    @Autowired
    @Qualifier("clients")
    private ExecutorService clients;

    public static void main(String[] args) {
        SpringApplication.run(GameServer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ServerSocket server = null;
        try {
            server = new ServerSocket(3333);
            log.debug("Started server");
            while (true) {
                final Socket socket = server.accept();
                log.debug("Accepted connection from {}", socket.getRemoteSocketAddress());
                clients.execute(new ClientTask(socket));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (server != null) {
                try {
                    server.close();
                    clients.shutdown();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

    }
}
