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
class FilterLoggerTest {

    @Mock
    private BaseLogger parent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void assertParent() {
        Assertions.assertNotNull(parent);
        final FilterLogger logger = new FilterLogger(Logger.LEVEL_VERBOSE, parent);
        Assertions.assertEquals(parent, logger.target);
    }

    @Test
    void assertLevel() {
        Assertions.assertNotNull(parent);
        Assertions.assertEquals(Logger.LEVEL_VERBOSE, (new FilterLogger(Logger.LEVEL_VERBOSE, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_DEBUG, (new FilterLogger(Logger.LEVEL_DEBUG, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_INFO, (new FilterLogger(Logger.LEVEL_INFO, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_WARNING, (new FilterLogger(Logger.LEVEL_WARNING, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_ERROR, (new FilterLogger(Logger.LEVEL_ERROR, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_ASSERT, (new FilterLogger(Logger.LEVEL_ASSERT, parent)).level);
        Assertions.assertEquals(Logger.LEVEL_NONE, (new FilterLogger(Logger.LEVEL_NONE, parent)).level);
    }
}