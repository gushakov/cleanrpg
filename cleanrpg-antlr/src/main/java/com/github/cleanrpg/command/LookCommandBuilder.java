package com.github.cleanrpg.command;

import com.github.cleanrpg.antlr4.CommandParser;
import com.github.cleanrpg.model.command.Command;
import com.github.cleanrpg.model.command.LookCommand;

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
