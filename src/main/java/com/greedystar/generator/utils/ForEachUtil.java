package com.greedystar.generator.utils;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author GreedyStar
 * @date 2020/8/1
 */
public class ForEachUtil {

    /**
     * 带索引的foreach工具
     *
     * @param consumer
     * @param <T>
     * @return
     */
    public static <T> Consumer<T> withIndex(BiConsumer<T, Integer> consumer) {
        Index index = new Index();
        return t -> {
            int i = index.value++;
            consumer.accept(t, i);
        };
    }

    private static class Index {
        int value;
    }

}
