package ru.kamuzta.bankingrmi.server;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT,
    LANGUAGE;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        if (i < 1 || i > 4) {
            throw new IllegalArgumentException();
        }
        return Operation.values()[i];
    }
}
