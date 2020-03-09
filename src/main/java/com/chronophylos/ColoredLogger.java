package com.chronophylos;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.*;

public final class ColoredLogger extends Logger {
    private final static String COLOR_RED = "&c";
    private final static String COLOR_GOLD = "&6";
    private final static String COLOR_GRAY = "&7";
    private final static String COLOR_DARKGRAY = "&8";
    private final static String COLOR_RESET = "&r";

    public ColoredLogger(Plugin context) {
        super(context.getDescription().getName(), null);
        setParent(context.getServer().getLogger());
        setLevel(Level.ALL);
    }

    /**
     * Print a exception to the server console.
     * First line is the error message.
     * All following lines are from the stack trace.
     *
     * @param e exception to print
     */
    public void exception(Exception e) {
        StringWriter stringWriter = new StringWriter();

        e.printStackTrace(new PrintWriter(stringWriter));

        severe(stringWriter.toString());

        try {
            stringWriter.close();
        } catch (IOException ioException) {
            severe("Exception when closing StringWriter: " + ioException);
        }
    }

    @Override
    public void log(LogRecord logRecord) {
        logRecord.setMessage(ChatColor.translateAlternateColorCodes('&', logRecord.getMessage()));

        super.log(logRecord);
    }

    @Override
    public void severe(String var1) {
        log(Level.SEVERE, COLOR_RED + var1 + COLOR_RESET);
    }

    @Override
    public void warning(String var1) {
        log(Level.WARNING, COLOR_GOLD + var1 + COLOR_RESET);
    }

    @Override
    public void info(String var1) {
        log(Level.INFO, var1);
    }

    @Override
    public void config(String var1) {
        log(Level.CONFIG, var1);
    }

    @Override
    public void fine(String var1) {
        log(Level.FINE, COLOR_GRAY + var1 + COLOR_RESET);
    }

    @Override
    public void finer(String var1) {
        log(Level.FINER, COLOR_GRAY + var1 + COLOR_RESET);
    }

    @Override
    public void finest(String var1) {
        log(Level.FINEST, COLOR_DARKGRAY + var1 + COLOR_RESET);
    }
}
