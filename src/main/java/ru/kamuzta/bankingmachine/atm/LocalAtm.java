package ru.kamuzta.bankingmachine.atm;

import ru.kamuzta.bankingmachine.*;
import ru.kamuzta.bankingmachine.server.*;
import ru.kamuzta.bankingmachine.server.exception.*;
import ru.kamuzta.bankingmachine.server.command.*;

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
