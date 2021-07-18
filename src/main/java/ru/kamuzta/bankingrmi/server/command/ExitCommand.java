package ru.kamuzta.bankingrmi.server.command;

import ru.kamuzta.bankingrmi.*;
import ru.kamuzta.bankingrmi.server.*;
import ru.kamuzta.bankingrmi.server.exception.*;

import java.util.ResourceBundle;

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
