package ru.kamuzta.bankingrmi.server;

import ru.kamuzta.bankingrmi.server.exception.InterruptOperationException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankRmi extends Remote {
    void execute(Operation operation) throws RemoteException, InterruptOperationException;
}
