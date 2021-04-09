package ru.kamuzta.bankingrmi;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//This is Server program to start
public class ProcessingServer {
    private static final String UNIQUE_BINDING_NAME = "cardserver";
    private static Registry registry = null;
    public static void main(String[] args) {
        try {
            final BankRmiObject server = new BankRmiObject();
            System.out.println("BankRmiObject created");
            registry = LocateRegistry.createRegistry(2732);
            System.out.println("Registry created");
            Remote stub = UnicastRemoteObject.exportObject(server, 0);
            registry.bind(UNIQUE_BINDING_NAME, stub);
            System.out.println(UNIQUE_BINDING_NAME + " binded");
            System.out.println("Ready to work with ATM!");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
