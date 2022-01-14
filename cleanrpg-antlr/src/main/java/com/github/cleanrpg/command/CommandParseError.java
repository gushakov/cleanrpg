package com.github.cleanrpg.command;

public class CommandParseError extends RuntimeException {
    public CommandParseError(String message) {
        super(message);
    }

    public CommandParseError(Throwable cause) {
        super(cause);
    }
}
