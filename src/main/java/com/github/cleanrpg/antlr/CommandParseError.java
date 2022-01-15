package com.github.cleanrpg.antlr;

public class CommandParseError extends RuntimeException {
    public CommandParseError(String message) {
        super(message);
    }

    public CommandParseError(Throwable cause) {
        super(cause);
    }
}
