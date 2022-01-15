package com.github.cleanrpg.antlr;

import com.github.cleanrpg.model.command.Command;
import org.antlr.v4.runtime.ParserRuleContext;

public interface CommandBuilder<C extends ParserRuleContext> {

    void onParseEnd(C parserContext);

    Command getCommand();

}
