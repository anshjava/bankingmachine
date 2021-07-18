package ru.kamuzta.bankingrmi.server.command;

import ru.kamuzta.bankingrmi.server.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
