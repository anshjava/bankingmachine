package ru.kamuzta.bankingrmi.server.command;


import ru.kamuzta.bankingrmi.*;
import ru.kamuzta.bankingrmi.server.*;
import ru.kamuzta.bankingrmi.server.exception.*;

import java.util.ResourceBundle;

class LoginCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("login.before"));

        while (true) {
            ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("login.specify.data"));
            String creditCardNumber = ConsoleHelper.readString();
            String pinStr = ConsoleHelper.readString();
            if (creditCardNumber == null || (creditCardNumber = creditCardNumber.trim()).length() != 12 ||
                    pinStr == null || (pinStr = pinStr.trim()).length() != 4) {
                ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("login.try.again.with.details"));
            } else {
                try {
                    if (ConsoleHelper.CC.containsKey(creditCardNumber) && pinStr.equals(ConsoleHelper.CC.getString(creditCardNumber))) {
                        ConsoleHelper.writeMessage(String.format(ConsoleHelper.LANGUAGE.getString("login.success.format"), creditCardNumber));
                        break;
                    } else {
                        ConsoleHelper.writeMessage(String.format(ConsoleHelper.LANGUAGE.getString("login.not.verified.format"), creditCardNumber));
                        ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("login.try.again.or.exit"));
                    }
                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("login.try.again.with.details"));
                }
            }
        }

    }
}
