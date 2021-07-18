package ru.kamuzta.bankingmachine.server.command;

import ru.kamuzta.bankingmachine.server.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
