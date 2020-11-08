package ru.kamuzta.bankingrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankRmi extends Remote {
    String createCard(String cardHolderName) throws RemoteException;
    String getCardBalance(long cardNumber) throws RemoteException;
    String depositMoney(long cardNumber, int money) throws RemoteException;
    String withdrawMoney(long cardNumber, int money) throws RemoteException;
    String getCardList() throws RemoteException;
}
