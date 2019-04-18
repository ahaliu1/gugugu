package cn.whu.gugugu.utils;

import java.util.UUID;

public class UID {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
