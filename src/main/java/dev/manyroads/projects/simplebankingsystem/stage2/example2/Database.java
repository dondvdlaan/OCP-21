package dev.manyroads.projects.simplebankingsystem.stage2.example2;

import java.util.*;

public class Database {
    private final List<CreditCard> creditCards = new ArrayList<>();

    public boolean hasCreditCard(String number) { return this.getCreditCard(number) != null; }

    public void addCreditCard(CreditCard creditCard) { this.creditCards.add(creditCard); }

    public CreditCard getCreditCard(String number) {
        for (CreditCard creditCard : this.creditCards) {
            if (creditCard.getNumber().equals(number)) {
                return creditCard;
            }
        }
        return null;
    }
}
