package com.github.cleanrpg.model.command;

/**
 * Value object modeling a command as entered by a player.
 * @see CommandType
 */
public interface Command {

    CommandType getType();
}
