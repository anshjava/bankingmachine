package ru.kamuzta.bankingrmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Atm {
    public static final String UNIQUE_BINDING_NAME = "cardserver";
    public static Registry registry;

    public static void main(String[] args) {
        System.out.println("ATM started! Aviable commands:\n" +
                "create cardHolderName - create new card for cardHolderName\n" +
                "get cardNumber - get balance from specified cardNumber\n" +
                "deposit cardNumber money - deposit money to specified cardNumber\n" +
                "withdraw cardNumber money - withdraw money from specified cardNumber\n" +
                "list - get list of all cards from server\n" +
                "exit - close ATM\n");
        try {
            //connecting to registry on port 2732
            registry = LocateRegistry.getRegistry(2732);
            //getting object with name "cardserver" from registry
            BankRmi bank = (BankRmi) registry.lookup(UNIQUE_BINDING_NAME);
            boolean cont = true;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (cont) {
                System.out.print("#");
                String command = br.readLine();
                String[] commandArray = command.split(" ");

                switch (commandArray[0]) {
                    case ("create"):
                        try {
                            System.out.println(bank.createCard(commandArray[1]));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error, enter CardHolderName after command create");
                        }
                        break;
                    case ("get"):
                        try {
                            System.out.println(bank.getCardBalance(Long.parseLong(commandArray[1])));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error, enter cardNumber after command get");
                        }
                        break;
                    case ("deposit"):
                        try {
                            System.out.println(bank.depositMoney(Long.parseLong(commandArray[1]), Integer.parseInt(commandArray[2])));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error, enter cardNumber and quantity after command deposit");
                        }
                        break;
                    case ("withdraw"):
                        try {
                            System.out.println(bank.withdrawMoney(Long.parseLong(commandArray[1]), Integer.parseInt(commandArray[2])));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error, enter cardNumber and quantity after command withdraw");
                        }
                        break;
                    case ("list"):
                        System.out.println(bank.getCardList());
                        break;
                    case ("exit"):
                        cont = false;
                        break;
                    default:
                        System.out.println("Wrong command!, use create, get, deposit, withdraw, list, exit");
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
