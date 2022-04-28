package br.dev.dig.logger.union;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.dev.dig.logger.BaseLogger;

@ExtendWith(MockitoExtension.class)
class UnionLoggerTest_Creation {

    @Mock
    private BaseLogger parent_1;
    @Mock
    private BaseLogger parent_2;
    @Mock
    private BaseLogger parent_3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSingle_Args() {
        Assertions.assertNotNull(parent_1);
        final UnionLogger logger = UnionLogger.create(parent_1);
        Assertions.assertTrue(logger.getLoggers() instanceof ArrayList, "Internal list must be ArrayList");
        Assertions.assertEquals(1, logger.getLoggers().size(), "Loggers size must be one");
        Assertions.assertEquals(parent_1, logger.getLoggers().get(0));
    }

    @Test
    void testSingle_Collection() {
        Assertions.assertNotNull(parent_1);
        final Set<BaseLogger> source = new HashSet<>();
        source.add(parent_1);

        final UnionLogger logger = UnionLogger.create(source);
        Assertions.assertTrue(logger.getLoggers() instanceof ArrayList, "Internal list must be ArrayList");
        Assertions.assertEquals(1, logger.getLoggers().size(), "Loggers size must be one");
        Assertions.assertEquals(parent_1, logger.getLoggers().get(0));
    }

    @Test
    void testSingle_ArrayList() {
        Assertions.assertNotNull(parent_1);
        final List<BaseLogger> source = new ArrayList<>();
        source.add(parent_1);

        final UnionLogger logger = UnionLogger.create(source);
        Assertions.assertTrue(logger.getLoggers() instanceof ArrayList, "Internal list must be ArrayList");
        Assertions.assertEquals(source, logger.getLoggers(), "Must reuse arg ArrayList");
        Assertions.assertEquals(1, logger.getLoggers().size(), "Loggers size must be one");
        Assertions.assertEquals(parent_1, logger.getLoggers().get(0));
    }

    @Test
    void testDuo_Args() {
        Assertions.assertNotNull(parent_1);
        Assertions.assertNotNull(parent_2);
        final UnionLogger logger = UnionLogger.create(parent_1, parent_2);
        Assertions.assertTrue(logger.getLoggers() instanceof ArrayList, "Internal list must be ArrayList");
        Assertions.assertEquals(2, logger.getLoggers().size(), "Loggers size must be one");
        Assertions.assertEquals(parent_1, logger.getLoggers().get(0));
        Assertions.assertEquals(parent_2, logger.getLoggers().get(1));
    }

    @Test
    void testDuo_Collection() {
        Assertions.assertNotNull(parent_1);
        Assertions.assertNotNull(parent_2);
        final HashSet<BaseLogger> source = new HashSet<>(2);
        source.add(parent_1);
        source.add(parent_2);

        final UnionLogger logger = UnionLogger.create(source);
        Assertions.assertTrue(logger.getLoggers() instanceof ArrayList, "Internal list must be ArrayList");
        Assertions.assertEquals(2, logger.getLoggers().size(), "Loggers size must be two");
        // Can not assert items order from HashSet()
    }

    @Test
    void testDuo_List() {
        Assertions.assertNotNull(parent_1);
        Assertions.assertNotNull(parent_2);
        final ArrayList<BaseLogger> source = new ArrayList<>(2);
        source.add(parent_1);
        source.add(parent_2);

        final UnionLogger logger = UnionLogger.create(source);
        Assertions.assertTrue(logger.getLoggers() instanceof ArrayList, "Internal list must be ArrayList");
        Assertions.assertEquals(source, logger.getLoggers(), "Must reuse arg ArrayList");
        Assertions.assertEquals(2, logger.getLoggers().size(), "Loggers size must be two");
        Assertions.assertEquals(parent_1, logger.getLoggers().get(0));
        Assertions.assertEquals(parent_2, logger.getLoggers().get(1));
    }

    @Test
    void testReuse() {
        Assertions.assertNotNull(parent_1);
        Assertions.assertNotNull(parent_2);
        Assertions.assertNotNull(parent_3);
        final UnionLogger first = UnionLogger.create(parent_1, parent_2);
        final UnionLogger last = UnionLogger.create(parent_3, first);
        Assertions.assertTrue(last.getLoggers() instanceof ArrayList, "Internal list must be ArrayList");
        Assertions.assertEquals(3, last.getLoggers().size(), "Loggers size must be three");

        last.remove(parent_3);
        Assertions.assertEquals(2, last.getLoggers().size(), "Loggers size must be two");

        final HashSet<BaseLogger> source = new HashSet<>(2);
        source.add(last);
        source.add(parent_3);
        final UnionLogger logger = UnionLogger.create(source);
        Assertions.assertEquals(3, last.getLoggers().size(), "Loggers size must be three");
        Assertions.assertEquals(first, logger, "UnionLogger must use inner UnionLogger to avoid creating weird tree");
    }

}