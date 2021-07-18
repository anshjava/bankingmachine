package ru.kamuzta.bankingmachine.server;

import ru.kamuzta.bankingmachine.server.exception.*;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination)+count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int result = 0;

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            result += entry.getKey()*entry.getValue();
        }

        return result;
    }

    public boolean hasMoney() {
       return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        int withdrawedAmount = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addAll(denominations.keySet());
        queue.sort(Comparator.reverseOrder());

        while (withdrawedAmount < expectedAmount) {
            if (queue.size() == 0) {
                throw new NotEnoughMoneyException();
            }
            Integer amount = 0;
            Integer denomination = queue.poll();
            Integer totalAmount = denominations.get(denomination);
            if (denomination <= (expectedAmount - withdrawedAmount)) {
                for (int i = 1; i <= totalAmount; i++) {
                    int sum = i * denomination;
                    if (sum + withdrawedAmount > expectedAmount) {
                        amount = i - 1;
                        break;
                    }
                    amount = i;
                }
            }

            withdrawedAmount = withdrawedAmount + denomination * amount;
            if (amount != 0) {
                map.put(denomination, amount);
            }

        }
        //убираем нужное количество банкнот из исходной мапы хранения
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                denominations.put(entry.getKey(), denominations.get(entry.getKey()) - entry.getValue());
        }
        return map;
    }
}
