package com.laiteam.echowall.service.util;

import java.util.List;
import java.util.Optional;

public class OptionalUtils {

    private OptionalUtils() {
    }

    public static <E> Optional<E> getFirstNullableItem(List<E> collections) {
        return Optional.ofNullable(collections.isEmpty() ? null : collections.get(0));
    }
}
