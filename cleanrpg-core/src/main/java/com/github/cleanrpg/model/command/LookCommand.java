package com.github.cleanrpg.model.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LookCommand implements Command {

    @EqualsAndHashCode.Include
    @Override
    public CommandType getType() {
        return CommandType.LOOK;
    }

    Object target;
}
