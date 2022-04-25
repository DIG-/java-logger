package br.dev.dig.logger.intrinsics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;

public abstract class Intrinsics {
    private Intrinsics() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    @FunctionalInterface
    public interface Default<T> {
        @NotNull
        T create();
    }

    @NotNull
    public static <T> T parameterNotNull(@Nullable final T item, @NotNull final String message) {
        if (item == null) {
            throw new InvalidParameterException(message);
        }
        return item;
    }

    @NotNull
    public static <T> T ifIsNull(@Nullable final T item, @NotNull final Default<T> creator) {
        if (item == null) {
            return creator.create();
        }
        return item;
    }
}