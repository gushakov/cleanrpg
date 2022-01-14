package com.github.cleanrpg.command;

/**
 * Value object modeling a command as entered by a player.
 * @see CommandType
 */
public interface Command {

    CommandType getType();
}
