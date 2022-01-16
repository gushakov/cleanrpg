package com.github.cleanrpg.server;

import com.github.cleanrpg.antlr.CommandParseError;
import com.github.cleanrpg.antlr.Commands;
import com.github.cleanrpg.model.command.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Slf4j
public class ClientTask implements Runnable {

    private final Socket socket;

    @Override
    public void run() {

        try {
            // PuTTY works with ISO_8859_1 encoding
            final BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.ISO_8859_1));
            String rawInput;
            while ((rawInput = socketReader.readLine()) != null) {

                // windows telnet sends a control character for the first line
                if (rawInput.length() > 0 && rawInput.codePointAt(0) != 255) {

                    // convert input to UTF-8 and get rid of all control characters
                    final String line = new String(rawInput.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8)
                            .trim().replaceAll("[\u0000-\u001f]", "");
                    log.debug("Command line to parse: {}", line);

                    try {
                        // parse the command
                        Command command = Commands.parseCommand(line);

                        log.debug("Parsed command: {}", command);
                    } catch (CommandParseError e) {
                        // ignore command parse errors
                        log.error(e.getMessage());
                    }

                    if (line.equals("quit")) {
                        break;
                    }
                }
            }
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
        }

    }
}
