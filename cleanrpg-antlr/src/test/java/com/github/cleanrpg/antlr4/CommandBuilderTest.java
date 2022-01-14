package com.github.cleanrpg.antlr4;

import com.github.cleanrpg.command.CommandParseError;
import com.github.cleanrpg.model.command.CommandType;
import com.github.cleanrpg.command.Commands;
import com.github.cleanrpg.model.command.LookCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandBuilderTest {

    @Test
    void parseLookCommand() {
        LookCommand command = (LookCommand) Commands.parseCommand("look");
        assertThat(command.getType()).isEqualTo(CommandType.LOOK);
    }

    @Test
    void parseUnknownCommand() {
        assertThrows(CommandParseError.class, () -> Commands.parseCommand("foobar"));
    }
}
