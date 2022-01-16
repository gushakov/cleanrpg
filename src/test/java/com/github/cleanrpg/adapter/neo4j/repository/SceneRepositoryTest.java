package com.github.cleanrpg.adapter.neo4j.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.Set;

// using example from https://bit.ly/3qxNI5v

@DataNeo4jTest
public class SceneRepositoryTest {

    private static Neo4j embeddedDbServer;

    @Autowired
    private SceneRepository sceneRepository;

    @BeforeAll
    static void beforeAll() {
        embeddedDbServer = Neo4jBuilders.newInProcessBuilder()
                .withDisabledServer().build();
    }

    @AfterAll
    static void afterAll() {
        embeddedDbServer.close();
    }

    @DynamicPropertySource
    static void neo4jProps(DynamicPropertyRegistry registry){
        registry.add("spring.neo4j.uri", embeddedDbServer::boltURI);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", () -> null);
    }

    @Test
    void storeSimpleSceneAndQuery() {

        final SceneEntity s2 = new SceneEntity("s2", "s2", Set.of());

        final SceneEntity s1 = new SceneEntity("s1", "s1", Set.of(new Exit(s2, "n")));
        s2.getExits().add(new Exit(s1, "s"));

        sceneRepository.saveAll(Set.of(s1, s2));

    }
}
