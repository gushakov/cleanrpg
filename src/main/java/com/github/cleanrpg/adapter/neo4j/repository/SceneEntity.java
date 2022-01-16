package com.github.cleanrpg.adapter.neo4j.repository;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class SceneEntity {

    @Id @GeneratedValue
    private Long id;

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    @Relationship(type = "EXIT", direction = Relationship.Direction.OUTGOING)
    private final Set<Exit> exits;

    public SceneEntity(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashSet<>();
    }

    public SceneEntity(String name, String description, Set<Exit> exits) {
        this.name = name;
        this.description = description;
        this.exits = new HashSet<>(exits);
    }
}
