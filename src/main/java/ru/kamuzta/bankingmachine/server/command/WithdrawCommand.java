package ru.kamuzta.bankingmachine.server.command;


import ru.kamuzta.bankingmachine.*;
import ru.kamuzta.bankingmachine.server.*;
import ru.kamuzta.bankingmachine.server.exception.*;

import java.util.Map;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("withdraw.before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        boolean flag = true;
        while (flag) {
            int amountInt = 0;
            ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("withdraw.specify.amount"));
            String amount = ConsoleHelper.readString();

            try {
                amountInt = Integer.parseInt(amount);
                if (amountInt > 0 && manipulator.isAmountAvailable(amountInt)) {
                    flag = false;
                } else {
                    ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("withdraw.not.enough.money"));
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("withdraw.specify.not.empty.amount"));
            }
            if (!flag) {
                try {
                    Map<Integer, Integer> map = manipulator.withdrawAmount(amountInt);
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                    }
                    ConsoleHelper.writeMessage(String.format(ConsoleHelper.LANGUAGE.getString("withdraw.success.format"), amount, currencyCode));
                } catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("withdraw.exact.amount.not.available"));
                }
            }

        }




    }
}
