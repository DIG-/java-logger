package br.dev.dig.logger.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import br.dev.dig.logger.Logger;

public abstract class CachedLoggerBuilder extends LoggerBuilder {

    final Map<String, WeakReference<Logger>> cache = new HashMap<>();

    @Override
    @NotNull
    protected synchronized Logger createLogger(@Nullable String tag) {
        if (tag == null || tag.isEmpty()) {
            return super.createLogger(tag);
        }
        final WeakReference<Logger> cachedRef = cache.get(tag);
        if (cachedRef != null) {
            final Logger cached = cachedRef.get();
            if (cached != null) {
                return cached;
            }
        }
        final Logger instance = super.createLogger(tag);
        cache.put(tag, new WeakReference<>(instance));
        return instance;
    }

}