package com.github.cleanrpg.adapter.neo4j.repository;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Exit {

    @RelationshipId
    private Long id;

    @Getter
    @TargetNode
    private final SceneEntity to;

    @Getter
    private final String direction;

    public Exit(SceneEntity to, String direction) {
        this.to = to;
        this.direction = direction;
    }
}
