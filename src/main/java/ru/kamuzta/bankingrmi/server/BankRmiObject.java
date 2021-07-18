package ru.kamuzta.bankingrmi.server;

import ru.kamuzta.bankingrmi.ConsoleHelper;
import ru.kamuzta.bankingrmi.server.command.CommandExecutor;
import ru.kamuzta.bankingrmi.server.exception.InterruptOperationException;

import java.rmi.RemoteException;

public class BankRmiObject implements BankRmi {


    @Override
    public void execute(Operation operation) throws RemoteException, InterruptOperationException {
        CommandExecutor.execute(operation);
    }
}