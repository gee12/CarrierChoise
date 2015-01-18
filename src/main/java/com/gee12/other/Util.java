package com.gee12.other;

import java.util.Arrays;

/**
 *
 * @author Иван
 */
public class Util {
    
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }
}
