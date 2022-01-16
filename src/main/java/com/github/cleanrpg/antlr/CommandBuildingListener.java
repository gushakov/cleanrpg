package com.github.cleanrpg.antlr;

import com.github.cleanrpg.antlr4.CommandBaseListener;
import com.github.cleanrpg.antlr4.CommandParser;
import com.github.cleanrpg.model.command.Command;
import org.antlr.v4.runtime.ParserRuleContext;

public class CommandBuildingListener extends CommandBaseListener {

    private CommandBuilder<? extends ParserRuleContext> commandBuilder;

    @Override
    public void enterLook(CommandParser.LookContext ctx) {
        commandBuilder = new LookCommandBuilder(ctx);
    }

    @Override
    public void exitLook(CommandParser.LookContext ctx) {

        if (commandBuilder instanceof LookCommandBuilder) {
            ((LookCommandBuilder)commandBuilder).onParseEnd(ctx);
        } else {
            error();
        }
    }

    public Command getCommand() {
        if (commandBuilder == null) {
            throw new CommandParseError("Command builder is null");
        }
        return commandBuilder.getCommand();
    }

    private void error() {
        throw new CommandParseError(String.format("Unexpected command builder type: %s",
                commandBuilder.getClass()));
    }
}
