package ru.kamuzta.bankingrmi.atm;

import ru.kamuzta.bankingrmi.ConsoleHelper;
import ru.kamuzta.bankingrmi.server.*;
import ru.kamuzta.bankingrmi.server.command.CommandExecutor;
import ru.kamuzta.bankingrmi.server.exception.InterruptOperationException;

import java.io.*;
import java.rmi.registry.*;
import java.util.Locale;

public class RemoteAtm {
    private static final String UNIQUE_BINDING_NAME = "bankingRMIServer";
    private static Registry registry;

    public static void main(String[] args) {
        Locale.setDefault(Language.DEFAULT.getLocale());
        System.out.println("ATM started!");
        try {
            //connecting to registry on port 2727
            registry = LocateRegistry.getRegistry(2727);
            //getting object with specified name from registry
            BankRmi bank = (BankRmi) registry.lookup(UNIQUE_BINDING_NAME);
            System.out.println("Connected to Bank successfully");
            bank.execute(Operation.LANGUAGE);

        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
