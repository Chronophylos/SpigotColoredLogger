package com.chronophylos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.logging.*;

public final class ColoredLogger extends Logger {

    /**
     * Get the current date as a formatted string.
     * @return a string with the formatted current date
     */
    @NotNull
    private static String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(System.currentTimeMillis());
    }

    public ColoredLogger(@NotNull Plugin context) {
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
    public void exception(@NotNull Exception e) {
        //noinspection resource
        StringWriter stringWriter = new StringWriter();

        e.printStackTrace(new PrintWriter(stringWriter));

        severe("&c" + stringWriter.toString());

        try {
            stringWriter.close();
        } catch (IOException ignored) {}
    }

    @Override
    public void log(@NotNull LogRecord logRecord) {
        // Since the default logger doesn't care about logging levels we'll do it here
        if (isLoggable(logRecord.getLevel())) {
            String prefix = "[" + getName() + "]";
            String message = prefix + "[" + logRecord.getLevel().getName() + "] " +
                    ChatColor.translateAlternateColorCodes('&', logRecord.getMessage());

            Bukkit.getConsoleSender().sendMessage(message);
        }
    }

    @Override
    public void severe(String var1) {
        log(Level.SEVERE, "&c" + var1);
    }

    @Override
    public void warning(String var1) {
        log(Level.WARNING, "&6" + var1);
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
        log(Level.FINE, "&7" + var1);
    }

    @Override
    public void finer(String var1) {
        log(Level.FINER, "&7" + var1);
    }

    @Override
    public void finest(String var1) {
        log(Level.FINEST, "&7" + var1);
    }
}
