package br.dev.dig.logger.printer.stub;

import org.junit.jupiter.api.Test;

import br.dev.dig.logger.BaseLogger;

class StubLoggerTest {

    @Test
    void dummy_coverage() {
        final BaseLogger logger = new StubLogger();
        logger.log(1, null, () -> "", null);
        logger.log(1, null, "", null);
    }

}