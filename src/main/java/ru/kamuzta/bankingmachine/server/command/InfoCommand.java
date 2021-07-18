package ru.kamuzta.bankingmachine.server.command;

import ru.kamuzta.bankingmachine.*;
import ru.kamuzta.bankingmachine.server.*;

import java.util.Collection;

class InfoCommand implements Command {
    @Override
    public void execute() {
        Collection<CurrencyManipulator> cm = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        boolean hasMoneyFlag = false;
        for (CurrencyManipulator manipulator : cm) {
            if (manipulator.hasMoney()) {
                hasMoneyFlag = true;
                ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("info.before"));
                ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
            }
        }
        if (!hasMoneyFlag) {
            ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("info.no.money"));
        }

    }
}
