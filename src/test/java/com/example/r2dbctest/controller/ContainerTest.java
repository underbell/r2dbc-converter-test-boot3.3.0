package com.example.r2dbctest.controller;

import java.time.Duration;
import java.util.function.Supplier;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

@SuppressWarnings({"rawtypes"})
public abstract class ContainerTest {
  static final Network network = Network.newNetwork();
  static final GenericContainer mysql =
      new GenericContainer(DockerImageName.parse("mysql:8.0.32"))
          .withExposedPorts(3306)
          .withEnv("MYSQL_DATABASE", "test")
          .withEnv("MYSQL_RANDOM_ROOT_PASSWORD", "yes")
          .withEnv("MYSQL_USER", "admin")
          .withEnv("MYSQL_PASSWORD", "password")
          .withCommand(
              "--character-set-server=utf8mb4 --collation-server=utf8mb4_general_ci --max_connections=500")
          .waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(60)))
          .withNetwork(network);

  @DynamicPropertySource
  static void databaseProperties(DynamicPropertyRegistry registry) {
    Startables.deepStart(mysql).join();

    Supplier<String> r2dbcUrl =
        () ->
            "r2dbc:mysql://admin:password@"
                + mysql.getHost()
                + ":"
                + mysql.getFirstMappedPort()
                + "/test?tcpKeepAlive=true&useServerPrepareStatement=true&tcpNoDelay=true&useSSL=false";

    registry.add("spring.r2dbc.url", r2dbcUrl::get);
  }
}
