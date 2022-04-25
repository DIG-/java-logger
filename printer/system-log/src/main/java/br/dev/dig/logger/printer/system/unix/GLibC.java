package br.dev.dig.logger.printer.system.unix;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.unix.LibCAPI;

@SuppressWarnings("SpellCheckingInspection")
interface GLibC extends LibCAPI, Library {
    GLibC INSTANCE = Native.load("c", GLibC.class);

    void openlog(String identity, int option, int facility);

    void syslog(int priority, String format, Object... args);

    void closelog();

}
