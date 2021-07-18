package ru.kamuzta.bankingrmi.server;

//This is card object
public class Card {
    private long cardNumber;
    private String cardHolderName;
    private int cardBalance;

    public Card(String cardHolderName) {
        this.cardHolderName = cardHolderName;
        this.cardBalance = 0;
        this.cardNumber = 5800500040000000L + (long)(Math.random()*999);
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public int getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(int cardBalance) {
        this.cardBalance = cardBalance;
    }

    @Override
    public String toString() {
        return cardNumber + " " + cardHolderName + " " + cardBalance;
    }
}
