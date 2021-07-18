package ru.kamuzta.bankingmachine;

import ru.kamuzta.bankingmachine.server.*;
import ru.kamuzta.bankingmachine.server.exception.*;

import java.io.*;
import java.util.ResourceBundle;

public class ConsoleHelper {
    public static ResourceBundle LANGUAGE = ResourceBundle.getBundle("language_en");
    public static ResourceBundle CC = ResourceBundle.getBundle("verifiedCards");

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String result = "";
        try {
            result = bis.readLine();
            if (result.toLowerCase().contains("exit")) {
                throw new InterruptOperationException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result;
        while (true) {
            writeMessage(LANGUAGE.getString("choose.currency.code"));
                result = readString();
                if (result != null && result.length() == 3)
                    break;
        }
        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(String.format(LANGUAGE.getString("choose.denomination.and.count.format"), currencyCode));
            String s = readString();
            String[] split;
            if (s == null || (split = s.split(" ")).length != 2) {
                writeMessage(LANGUAGE.getString("invalid.data"));
            } else {
                try {
                    if (Integer.parseInt(split[0]) <= 0 || Integer.parseInt(split[1]) <= 0)
                        writeMessage(LANGUAGE.getString("invalid.data"));
                } catch (NumberFormatException e) {
                    writeMessage(LANGUAGE.getString("invalid.data"));
                    continue;
                }
                return split;
            }
        }
    }



    public static Operation askOperation() throws InterruptOperationException {
        String input;
        Operation operation;
        while (true) {
            System.out.print("1 - " + LANGUAGE.getString("operation.INFO") +
                    " , 2 - " + LANGUAGE.getString("operation.DEPOSIT") +
                    " , 3 - " + LANGUAGE.getString("operation.WITHDRAW") +
                    " , 4 - " + LANGUAGE.getString("operation.EXIT") +
                    "\n" + LANGUAGE.getString("choose.operation") + ": ");
            input = readString();

            try {
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(input));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(LANGUAGE.getString("invalid.data"));
            }
        }
        return operation;
    }

    public static Language askLanguage() throws InterruptOperationException {
        String input;
        Language language;
        while (true) {
            System.out.print("1 - " + LANGUAGE.getString("language.ENG") +
                    " , 2 - " + LANGUAGE.getString("language.RUS") +
                    "\n" + LANGUAGE.getString("choose.language") + ": ");
            input = readString();

            try {
                language = Language.getAllowableLanguageByOrdinal(Integer.parseInt(input));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(LANGUAGE.getString("choose.language"));
            }
        }
        return language;
    }

    public static void printExitMessage() {
        writeMessage(LANGUAGE.getString("exit.thank.message"));
    }
}
