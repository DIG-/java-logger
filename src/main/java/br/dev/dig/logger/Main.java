package br.dev.dig.logger;

import br.dev.dig.logger.printer.println.PrintlnLogger;

public class Main {

    public static void main(String[] args) {
        Logger.setCreator(PrintlnLogger::create);
        final Logger log = Logger.getInstance();
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
