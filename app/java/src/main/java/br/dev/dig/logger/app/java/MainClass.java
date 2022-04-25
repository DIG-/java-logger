package br.dev.dig.logger.app.java;

import org.jetbrains.annotations.NotNull;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;
import br.dev.dig.logger.builder.LoggerBuilder;
import br.dev.dig.logger.printer.println.PrintlnLogger;
import br.dev.dig.logger.printer.system.SystemLogLogger;
import br.dev.dig.logger.union.UnionLogger;

public class MainClass {

    static final LoggerBuilder builder = new LoggerBuilder() {
        @Override
        @NotNull
        protected BaseLogger getBaseLogger() {
            return UnionLogger.create(new PrintlnLogger(), new SystemLogLogger.Builder().setAppName("SimpleLogger").build());
        }
    };

    public static void main(String[] args) {
        final Logger log = Logger.getInstance(builder);
        // or
        // final Logger log = builder.getLogger();
        log.debug("Test one");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            log.e("Failed to sleep", e);
        }
        log.w(() -> "With lambda");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.e("Failed to sleep", e);
        }
        try {
            //noinspection ConstantConditions,ResultOfMethodCallIgnored
            ((String) null).isEmpty();
        } catch (NullPointerException e) {
            log.e("Catch exception", e);
        }
        log.i("Finish");
    }

}