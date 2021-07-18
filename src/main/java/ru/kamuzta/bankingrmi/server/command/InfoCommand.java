package ru.kamuzta.bankingrmi.server.command;

import ru.kamuzta.bankingrmi.*;
import ru.kamuzta.bankingrmi.server.*;
import ru.kamuzta.bankingrmi.server.exception.*;

import java.util.Collection;
import java.util.ResourceBundle;

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
