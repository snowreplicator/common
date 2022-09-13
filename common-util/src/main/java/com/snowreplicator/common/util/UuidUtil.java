package com.snowreplicator.common.util;

import java.util.UUID;

public class UuidUtil {

    public static UUID generateUuid() {
        return UUID.randomUUID();
    }

    public static String generateUuidString() {
        return generateUuid().toString();
    }

}
