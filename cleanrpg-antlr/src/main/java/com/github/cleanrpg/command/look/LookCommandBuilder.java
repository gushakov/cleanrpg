package com.github.cleanrpg.command.look;

import com.github.cleanrpg.antlr4.CommandParser;
import com.github.cleanrpg.command.Command;
import com.github.cleanrpg.command.CommandBuilder;

public class LookCommandBuilder implements CommandBuilder<CommandParser.LookContext> {

    private final LookCommand.LookCommandBuilder builder;
    private LookCommand command;

    public LookCommandBuilder(CommandParser.LookContext parserContext) {
        this.builder = LookCommand.builder();
    }

    @Override
    public void onParseEnd(CommandParser.LookContext parserContext) {
        command = builder.build();
    }

    @Override
    public Command getCommand() {
        return command;
    }
}
