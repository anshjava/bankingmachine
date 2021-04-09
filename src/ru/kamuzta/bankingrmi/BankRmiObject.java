package ru.kamuzta.bankingrmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class BankRmiObject implements BankRmi {
    private static final List<Card> cardList = new ArrayList<>();

    @Override
    public String createCard(String cardHolderName) throws RemoteException {
        synchronized (cardList) {
            Card newCard = new Card(cardHolderName);
            cardList.add(newCard);
            String message = "Card # " + newCard.getCardNumber() + " created!";
            System.out.println(message);
            return message;
        }
    }

    @Override
    public synchronized String getCardBalance(long cardNumber) throws RemoteException {
        boolean status = false;
        String message = "";
        for (Card card : cardList) {
            if (card.getCardNumber() == cardNumber) {
                message = card.getCardNumber() + " balance is: " + card.getCardBalance();
                status = true;
            }
        }
        if (!status) {
            message = "Wrong cardNumber!";
        }

        System.out.println(message);
        return message;

    }

    @Override
    public String depositMoney(long cardNumber, int money) throws RemoteException {
        boolean status = false;
        String message = "";
        synchronized (cardList) {
            for (Card card : cardList) {
                if (card.getCardNumber() == cardNumber) {
                    card.setCardBalance(card.getCardBalance() + money);
                    message = "Added " + money + ". New balance of card " + card.getCardNumber() + " is " + card.getCardBalance();
                    status = true;
                }
            }
            if (!status) {
                message = "Wrong cardNumber!";
            }
            System.out.println(message);
            return message;
        }
    }

    @Override
    public String withdrawMoney(long cardNumber, int money) throws RemoteException {
        boolean status = false;
        String message = "";
        synchronized (cardList) {
            for (Card card : cardList) {
                if (card.getCardNumber() == cardNumber) {
                    if (money > card.getCardBalance()) {
                        message = "Not enough money on Card " + card.getCardNumber();
                        status = true;
                    } else {
                        card.setCardBalance(card.getCardBalance() - money);
                        message = money + " withdrawed. New balance of card " + card.getCardNumber() + " is " + card.getCardBalance();
                        status = true;
                    }
                }
            }
            if (!status) {
                message = "Wrong cardNumber!";
            }
            System.out.println(message);
            return message;
        }
    }

    @Override
    public String getCardList() throws RemoteException {
        StringBuilder sb = new StringBuilder();
        String message;
        synchronized (cardList) {
            for (Card card : cardList) {
                sb.append(card.toString());
                sb.append("\n");
            }
            message = sb.toString();

            System.out.println(message);
            return message;
        }
    }
}