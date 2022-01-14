package com.github.cleanrpg.command;

import com.github.cleanrpg.antlr4.CommandLexer;
import com.github.cleanrpg.antlr4.CommandParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Commands {

    public static Command parseCommand(String command) {
        CodePointCharStream charStream = CharStreams.fromString(command);
        CommandLexer lexer = new CommandLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CommandParser parser = new CommandParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        CommandBuildingListener listener = new CommandBuildingListener();
        try {
            new ParseTreeWalker().walk(listener, parser.command());
        } catch (Throwable e) {
            throw new CommandParseError(e);
        }
        CommandBuilder<? extends ParserRuleContext> commandBuilder = listener.getCommandBuilder();
        return commandBuilder.getCommand();
    }

}
