package com.github.cleanrpg.command;

import org.antlr.v4.runtime.ParserRuleContext;

public interface CommandBuilder<C extends ParserRuleContext> {

    void onParseEnd(C parserContext);

    Command getCommand();

}
