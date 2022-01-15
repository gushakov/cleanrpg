package com.github.cleanrpg.antlr;

import com.github.cleanrpg.antlr4.CommandBaseListener;
import com.github.cleanrpg.antlr4.CommandParser;
import lombok.Getter;
import org.antlr.v4.runtime.ParserRuleContext;

public class CommandBuildingListener extends CommandBaseListener {

    @Getter
    private CommandBuilder<? extends ParserRuleContext> commandBuilder;

    @Override
    public void enterLook(CommandParser.LookContext ctx) {
       commandBuilder = new LookCommandBuilder(ctx);
    }

    @Override
    public void exitLook(CommandParser.LookContext ctx) {

        if (commandBuilder instanceof LookCommandBuilder lookCommandBuilder){
            lookCommandBuilder.onParseEnd(ctx);
        }
        else {
            error();
        }
    }

    private void error(){
        throw new CommandParseError("Unexpected command builder type: %s"
                .formatted(commandBuilder.getClass()));
    }
}
