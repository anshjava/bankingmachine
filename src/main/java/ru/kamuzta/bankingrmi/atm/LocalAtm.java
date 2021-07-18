package ru.kamuzta.bankingrmi.atm;

import ru.kamuzta.bankingrmi.*;
import ru.kamuzta.bankingrmi.server.*;
import ru.kamuzta.bankingrmi.server.exception.*;
import ru.kamuzta.bankingrmi.server.command.*;

import java.util.Locale;

public class LocalAtm {
    public static void main(String[] args) {
        Locale.setDefault(Language.DEFAULT.getLocale());
        try {
            Operation operation;
            CommandExecutor.execute(Operation.LANGUAGE);
            CommandExecutor.execute(Operation.LOGIN);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
