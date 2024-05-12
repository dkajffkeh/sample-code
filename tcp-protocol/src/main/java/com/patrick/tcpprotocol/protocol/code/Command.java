package com.patrick.tcpprotocol.protocol.code;

import java.util.HashMap;
import java.util.Map;

public enum Command {

    SAMPLE_REQ(1010)
    ;

    private final int code;

    private static final Map<Integer, Command> valueToTypeMap = new HashMap();

    static {
        Command[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            Command type = var0[var2];
            valueToTypeMap.put(type.getCode(), type);
        }

    }

    public static Command fromValue(Integer value) throws IllegalArgumentException {
        Command type = valueToTypeMap.get(value);
        if (type == null) {
            throw new IllegalArgumentException("No enum constant " + value);
        } else {
            return type;
        }
    }


    Command(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
