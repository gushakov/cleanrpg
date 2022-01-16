package com.github.cleanrpg.antlr;

import com.github.cleanrpg.antlr4.CommandLexer;
import com.github.cleanrpg.antlr4.CommandParser;
import com.github.cleanrpg.model.command.Command;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
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
        return listener.getCommand();
    }

}