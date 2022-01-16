package com.github.cleanrpg.adapter.neo4j.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
@RequiredArgsConstructor
public class SceneEntity {

    @Id @GeneratedValue
    private Long id;

    private final String name;

    private final String description;

    @Relationship(type = "EXIT", direction = Relationship.Direction.OUTGOING)
    private final Set<ExitEntity> exits;

}
