package ru.kamuzta.bankingmachine.server.command;

import ru.kamuzta.bankingmachine.*;
import ru.kamuzta.bankingmachine.server.exception.*;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("exit.question.y.n"));
        String answer = ConsoleHelper.readString();
        if ("y".equals(answer)) {
            ConsoleHelper.writeMessage(ConsoleHelper.LANGUAGE.getString("exit.thank.message"));
        }
    }
}
