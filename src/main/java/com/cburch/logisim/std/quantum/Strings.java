package com.cburch.logisim.std.quantum;

import com.cburch.logisim.util.LocaleManager;
import com.cburch.logisim.util.StringGetter;

public class Strings {
    private static final LocaleManager source = new LocaleManager("logisim", "std");

    public static String get(String key) { return source.get(key); }

    public static StringGetter getter(String key) { return source.getter(key); }
}
