package ru.kamuzta.bankingmachine.server.command;

import ru.kamuzta.bankingmachine.*;
import ru.kamuzta.bankingmachine.server.*;
import ru.kamuzta.bankingmachine.server.exception.*;

class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("deposit.before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true) {
            String[] split = ConsoleHelper.getValidTwoDigits(currencyCode);
            try {
                int denomination = Integer.parseInt(split[0]);
                int count = Integer.parseInt(split[1]);
                manipulator.addAmount(denomination, count);
                ConsoleHelper.writeMessage(String.format(ConsoleHelper.LANGUAGE.getString("deposit.success.format"), (denomination * count), currencyCode));
                break;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("invalid.data"));
            }
        }
    }
}
