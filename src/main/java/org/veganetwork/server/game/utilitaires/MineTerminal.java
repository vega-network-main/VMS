package org.veganetwork.server.game.utilitaires;


import net.minestom.server.MinecraftServer;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class MineTerminal {
    private static final String PREFIX = "[Server Console]: ";
    private static volatile LineReader lineReader;
    private static volatile Terminal terminal;
    private static volatile boolean run = false;
    public static void startTerminalThread() {
        final Thread thread = new Thread(null, () -> {
            try {
                terminal = TerminalBuilder.builder().system(true).dumb(true).encoding("UTF-8").name("VMS Console").build();
            } catch (IOException IOE) {
                throw new RuntimeException(IOE);
            }
            lineReader = LineReaderBuilder.builder().terminal(terminal).build();
            run = true;
            while (run) {
                String input;
                try {
                    input = lineReader.readLine(PREFIX);
                    MinecraftServer.getCommandManager().execute(MinecraftServer.getCommandManager().getConsoleSender(), input);
                } catch (EndOfFileException exception) {
                    return;
                } catch (UserInterruptException exception) {
                    System.exit(0);
                    return;
                }
            }
        }, "VMS-Console.Service");
        thread.setDaemon(true);
        thread.start();
    }
    public static void stopTerminalThread() {
        run = false;
        if(terminal!=null) {
            try {
                terminal.close();
            } catch (IOException e) {
                MinecraftServer.LOGGER.error(String.format("CONSOLE THREAD ERROR: %s", e));
            }
            lineReader = null;
        }
    }

}
