package br.dev.dig.logger.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

@ExtendWith(MockitoExtension.class)
class FilterLoggerByLevelTest {

    @Mock
    private BaseLogger parent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void assertParent() {
        Assertions.assertNotNull(parent);
        final FilterLoggerByLevel logger = new FilterLoggerByLevel(Logger.LEVEL_VERBOSE, parent);
        Assertions.assertEquals(parent, logger.target);
    }

    @Test
    void assertLevel() {
        Assertions.assertNotNull(parent);
        Assertions.assertEquals(Logger.LEVEL_VERBOSE, (new FilterLoggerByLevel(Logger.LEVEL_VERBOSE, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_DEBUG, (new FilterLoggerByLevel(Logger.LEVEL_DEBUG, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_INFO, (new FilterLoggerByLevel(Logger.LEVEL_INFO, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_WARNING, (new FilterLoggerByLevel(Logger.LEVEL_WARNING, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_ERROR, (new FilterLoggerByLevel(Logger.LEVEL_ERROR, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_ASSERT, (new FilterLoggerByLevel(Logger.LEVEL_ASSERT, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_NONE, (new FilterLoggerByLevel(Logger.LEVEL_NONE, parent)).level);
    }
}