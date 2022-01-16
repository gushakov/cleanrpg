package com.github.cleanrpg.adapter.neo4j.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@RequiredArgsConstructor
public class ExitEntity {

    @Id
    @GeneratedValue
    private Long id;

    private final String direction;
}
